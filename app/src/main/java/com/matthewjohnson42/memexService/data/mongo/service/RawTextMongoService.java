package com.matthewjohnson42.memexService.data.mongo.service;

import com.matthewjohnson42.memexService.data.converter.RawTextMongoConverter;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.mongo.entity.RawTextMongo;
import com.matthewjohnson42.memexService.data.mongo.repository.RawTextMongoRepo;
import com.matthewjohnson42.memexService.data.DataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Data service for persistence logic on the RawText entity type
 */
@Service
public class RawTextMongoService extends DataService<String, RawTextDto, RawTextMongo> {

    // duplicates "repository" from parent to provide type-specific method access
    private RawTextMongoRepo rawTextMongoRepo;

    public RawTextMongoService(RawTextMongoConverter rawTextMongoConverter, RawTextMongoRepo rawTextMongoRepo) {
        super(rawTextMongoConverter, rawTextMongoRepo);
        this.rawTextMongoRepo = rawTextMongoRepo;
    }

    public Page<RawTextDto> getPage(Pageable pageable) {
        Page<RawTextMongo> rawTextMongoPage = rawTextMongoRepo.findAll(pageable);
        List<RawTextDto> rawTextDtos = new ArrayList<>();
        rawTextMongoPage.getContent().forEach(entity -> rawTextDtos.add(converter.convertEntity(entity)));
        return new PageImpl<>(rawTextDtos, rawTextMongoPage.getPageable(), rawTextMongoPage.getTotalElements());
    }

}
