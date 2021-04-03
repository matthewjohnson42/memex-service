package com.matthewjohnson42.personalMemexService.data.elasticsearch.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextESCompositeConverter;
import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextSearchResponseDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextESComposite;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRestTemplate;
import com.matthewjohnson42.personalMemexService.data.DataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawTextESService extends DataService<String, RawTextDto, RawTextES> {

    private RawTextESCompositeConverter rawTextESCompositeConverter;
    private RawTextESRestTemplate rawTextESRestTemplate;

    public RawTextESService(RawTextESConverter rawTextESConverter,
                            RawTextESCompositeConverter rawTextESCompositeConverter,
                            RawTextESRestTemplate rawTextESRestTemplate) {
        super(rawTextESConverter, rawTextESRestTemplate);
        this.rawTextESCompositeConverter = rawTextESCompositeConverter;
        this.rawTextESRestTemplate = rawTextESRestTemplate;
    }

    public Page<RawTextSearchResponseDto> search(String searchString,
                                   LocalDateTime startCreateDate,
                                   LocalDateTime endCreateDate,
                                   LocalDateTime startUpdateDate,
                                   LocalDateTime endUpdateDate,
                                   Pageable pageable) {
        Page<RawTextESComposite> rawTextESPage = rawTextESRestTemplate.getPageFromSearchString(
                searchString,
                startCreateDate,
                endCreateDate,
                startUpdateDate,
                endUpdateDate,
                pageable);
        List<RawTextSearchResponseDto> rawTextDtos = rawTextESPage.get().map(
                entity -> rawTextESCompositeConverter.convertEntity(entity)).collect(Collectors.toList()
        );
        return new PageImpl<>(rawTextDtos, rawTextESPage.getPageable(), rawTextESPage.getTotalElements());
    }

}
