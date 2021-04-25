package com.matthewjohnson42.memexService.data.mongo.service;

import com.matthewjohnson42.memexService.data.converter.RawTextMongoConverter;
import com.matthewjohnson42.memexService.data.dto.PageRequestDto;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.dto.RawTextSearchRequestDto;
import com.matthewjohnson42.memexService.data.mongo.entity.RawTextMongo;
import com.matthewjohnson42.memexService.data.mongo.repository.RawTextMongoRepo;
import com.matthewjohnson42.memexService.data.DataService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Data service for persistence logic on the RawText entity type
 */
@Service
public class RawTextMongoService extends DataService<String, RawTextDto, RawTextMongo> {

    private RawTextMongoRepo rawTextMongoRepo;

    public RawTextMongoService(RawTextMongoConverter rawTextMongoConverter, RawTextMongoRepo rawTextMongoRepo) {
        super(rawTextMongoConverter, rawTextMongoRepo);
        this.rawTextMongoRepo = rawTextMongoRepo;
    }

    public Page<RawTextDto> getPage(RawTextSearchRequestDto rawTextSearchRequestDto) {
        Pageable pageable = getPageable(rawTextSearchRequestDto);
        Page<RawTextMongo> rawTextMongoPage = rawTextMongoRepo.getByCreateDateTimeRange(
                rawTextSearchRequestDto.getStartCreateDate(),
                rawTextSearchRequestDto.getEndCreateDate(),
                pageable);
        return mongoPageToDtoPage(rawTextMongoPage);
    }

    public Page<RawTextDto> getPage(PageRequestDto pageRequestDto) {
        Pageable pageable = getPageable(pageRequestDto);
        Page<RawTextMongo> rawTextMongoPage = rawTextMongoRepo.findAll(pageable);
        return mongoPageToDtoPage(rawTextMongoPage);
    }

    private Pageable getPageable(PageRequestDto pageRequestDto) {
        String sortOrder = pageRequestDto.getSort();
        return PageRequest.of(pageRequestDto.getPageNumber(),
                pageRequestDto.getPageSize(),
                Sort.by(Sort.Direction.fromString(
                        !StringUtils.isEmpty(sortOrder) ? sortOrder : "DESC"),
                        "createDateTime"));
    }

    private Page<RawTextDto> mongoPageToDtoPage(Page<RawTextMongo> rawTextMongoPage) {
        List<RawTextDto> rawTextDtos = new ArrayList<>();
        rawTextMongoPage.getContent().forEach(entity -> rawTextDtos.add(converter.convertEntity(entity)));
        return new PageImpl<>(rawTextDtos, rawTextMongoPage.getPageable(), rawTextMongoPage.getTotalElements());
    }

}
