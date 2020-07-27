package com.matthewjohnson42.personalMemexService.logic.service;

import com.matthewjohnson42.personalMemexService.data.converter.DtoEntityConverter;
import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.converter.RawTextMongoConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRepo;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import com.matthewjohnson42.personalMemexService.data.mongo.repository.RawTextMongoRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataTransferService implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void transferRawTextToES() {
        new MongoToEsTranferrer<RawTextDto, RawTextMongo, RawTextES>(
                applicationContext.getBean(RawTextMongoRepo.class),
                applicationContext.getBean(RawTextMongoConverter.class),
                applicationContext.getBean(RawTextESRepo.class),
                applicationContext.getBean(RawTextESConverter.class),
                1000
        ).accept();
    }

    /**
     * @param <D> the DTO type of the data being transferred
     * @param <M> the Mongo Entity type of the data being transferred
     * @param <E> the Elasticsearch Entity type of the data being transferred
     */
    private class MongoToEsTranferrer<D, M, E> {
        Logger logger = LoggerFactory.getLogger(MongoToEsTranferrer.class);
        private MongoRepository<M, String> mongoRepository;
        private DtoEntityConverter<D, M> mongoDtoConverter;
        private ElasticsearchRepository<E, String> elasticsearchRepository;
        private DtoEntityConverter<D, E> elasticsearchConverter;
        private int batchSize;

        /**
         *
         * @param mongoRepository
         * @param mongoDtoConverter
         * @param elasticsearchRepository
         * @param elasticsearchConverter
         * @param batchSize
         */
        public MongoToEsTranferrer(MongoRepository mongoRepository,
                                   DtoEntityConverter mongoDtoConverter,
                                   ElasticsearchRepository elasticsearchRepository,
                                   DtoEntityConverter elasticsearchConverter,
                                   int batchSize) {
            this.mongoRepository = mongoRepository;
            this.elasticsearchRepository = elasticsearchRepository;
            this.mongoDtoConverter = mongoDtoConverter;
            this.elasticsearchConverter = elasticsearchConverter;
            this.batchSize = batchSize;
        }

        public void accept() {
            int mongoRequestRetryLimit = 10;
            long totalDocuments = mongoRepository.count();
            long totalPages = totalDocuments / batchSize;
            List<Long> pageNumbers = new ArrayList<>();
            for (long i = 0; i <= totalPages; i++) {
                pageNumbers.add(i);
            }
            pageNumbers.stream().forEach(pageNumber -> {
                boolean pageRetrieved = false;
                int retryCount = 0;
                Page<M> mongoPage = null;
                while (!pageRetrieved && retryCount < mongoRequestRetryLimit) {
                    try {
                        mongoPage = mongoRepository.findAll(PageRequest.of(pageNumber.intValue(), batchSize));
                        pageRetrieved = true;
                    } catch (Exception e0) {
                        logger.info("Error when attempting to retrieve from Mongo", e0);
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e1) {
                        }
                    }
                    retryCount++;
                }
                List<E> esEntities = new ArrayList<>();
                if (mongoPage != null) {
                    mongoPage.getContent().forEach(mongoEntity -> {
                        D dto = mongoDtoConverter.convertEntity(mongoEntity);
                        E esEntity = elasticsearchConverter.convertDto(dto);
                        esEntities.add(esEntity);
                    });
                }
                elasticsearchRepository.saveAll(esEntities);
            });
        }
    }

}
