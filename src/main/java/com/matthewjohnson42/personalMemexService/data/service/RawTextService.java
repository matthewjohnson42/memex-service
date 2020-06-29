package com.matthewjohnson42.personalMemexService.data.service;

import com.matthewjohnson42.personalMemexService.data.dto.IdListDTO;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDTO;
import com.matthewjohnson42.personalMemexService.data.entity.RawTextEntity;
import com.matthewjohnson42.personalMemexService.data.repository.RawTextMongoRepo;
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
public class RawTextService {

    Logger logger = LoggerFactory.getLogger(RawTextService.class);
    RawTextMongoRepo rawTextMongoRepo;

    public RawTextService(RawTextMongoRepo rawTextMongoRepo) {
        this.rawTextMongoRepo = rawTextMongoRepo;
    }

    public IdListDTO getSummaries() {
        List<RawTextEntity> rawTextEntityList = rawTextMongoRepo.findAll();
        IdListDTO idListDTO = new IdListDTO();
        rawTextEntityList.forEach(e -> {
            idListDTO.addId(e.getId());
        });
        return idListDTO;
    }

    public RawTextDTO getById(String id) {
        RawTextEntity entity = getIfExists(id);
        return new RawTextDTO(entity.getText()).setId(entity.getId());
    }

    public RawTextDTO create(RawTextDTO dto) {
        RawTextEntity entity = new RawTextEntity(dto.getText());
        entity = rawTextMongoRepo.insert(entity);
        logger.info(String.format("Wrote new rawText object with id %s", entity.getId()));
        return new RawTextDTO(entity.getText()).setId(entity.getId());
    }

    public RawTextDTO update(RawTextDTO dto) {
        RawTextEntity entity = getIfExists(dto.getId());
        entity.setText(dto.getText());
        entity = rawTextMongoRepo.save(entity);
        return new RawTextDTO(entity.getText()).setId(entity.getId()); // todo split out to DTO and entity mapper/transformer
    }

    public void deleteById(String id) {
        getIfExists(id);
        rawTextMongoRepo.deleteById(id);
        logger.info(String.format("Deleted rawText object with id %s", id));
    }

    private RawTextEntity getIfExists(String id) { // todo add abstract data service containing methods like this
        Optional<RawTextEntity> rawTextEntity = rawTextMongoRepo.findById(id);
        if (rawTextEntity.isPresent()) {
            return rawTextEntity.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No entity found for id %s", id));
        }
    }

}
