package com.matthewjohnson42.personalMemexService.data.persistence.mongo.service;

import com.matthewjohnson42.personalMemexService.data.converter.rawtext.RawTextConverterMongoImpl;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.persistence.mongo.entity.RawTextMongo;
import com.matthewjohnson42.personalMemexService.data.persistence.mongo.repository.RawTextMongoRepo;
import com.matthewjohnson42.personalMemexService.data.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data service for persistence logic on the RawText entity type
 */
@Service
public class RawTextMongoService extends DataService<RawTextDto, RawTextMongo> {

    Logger logger = LoggerFactory.getLogger(RawTextMongoService.class);
    private RawTextMongoRepo rawTextMongoRepo;

    public RawTextMongoService(RawTextConverterMongoImpl rawTextConverterMongoImpl, RawTextMongoRepo rawTextMongoRepo) {
        this.converter = rawTextConverterMongoImpl;
        this.rawTextMongoRepo = rawTextMongoRepo;
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

    public Page<RawTextDto> getPage(Pageable pageable) {
        Page<RawTextMongo> rawTextMongoPage = rawTextMongoRepo.findAll(pageable);
        List<RawTextDto> rawTextDtos = new ArrayList<>();
        rawTextMongoPage.getContent().forEach(entity -> rawTextDtos.add(converter.convertEntity(entity)));
        return new PageImpl<>(rawTextDtos, rawTextMongoPage.getPageable(), rawTextMongoPage.getTotalElements());
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
