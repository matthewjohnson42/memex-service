package com.matthewjohnson42.personalMemexService.data.mongo.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextMongoConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import com.matthewjohnson42.personalMemexService.data.mongo.repository.RawTextMongoRepo;
import com.matthewjohnson42.personalMemexService.data.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * Data service for persistence logic on the RawText entity type
 */
@Service
public class RawTextMongoService extends DataService<RawTextDto, RawTextMongo> {

    Logger logger = LoggerFactory.getLogger(RawTextMongoService.class);
    private RawTextMongoRepo rawTextMongoRepo;

    public RawTextMongoService(RawTextMongoConverter rawTextMongoConverter, RawTextMongoRepo rawTextMongoRepo) {
        this.converter = rawTextMongoConverter;
        this.rawTextMongoRepo = rawTextMongoRepo;
    }

    public List<RawTextMongo> getSummaries() {
         return rawTextMongoRepo.findAll();
    }

    public RawTextDto getById(String id) {
        RawTextMongo entity = getIfExists(id);
        return converter.convertEntity(entity);
    }

    public RawTextDto create(RawTextDto dto) {
        RawTextMongo entity = converter.convertDto(dto).setId(null);
        entity = rawTextMongoRepo.save(entity);
        logger.info(String.format("Wrote new rawText object with id %s", entity.getId()));
        return converter.convertEntity(entity);
    }

    public RawTextDto update(RawTextDto dto) {
        RawTextMongo entity = getIfExists(dto.getId());
        entity = converter.updateFromDto(entity, dto);
        entity = rawTextMongoRepo.save(entity);
        logger.info(String.format("Updated rawText object with id %s", entity.getId()));
        return converter.convertEntity(entity);
    }

    public RawTextDto deleteById(String id) {
        RawTextMongo entity = getIfExists(id);
        rawTextMongoRepo.deleteById(id);
        logger.info(String.format("Deleted rawText object with id %s", id));
        return converter.convertEntity(entity);
    }

    private RawTextMongo getIfExists(String id) { // todo add abstract data service containing methods like this
        Optional<RawTextMongo> rawTextEntity = rawTextMongoRepo.findById(id);
        if (rawTextEntity.isPresent()) {
            return rawTextEntity.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No entity found for id %s", id));
        }
    }

}
