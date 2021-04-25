package com.matthewjohnson42.memexService.data.elasticsearch.service;

import com.matthewjohnson42.memexService.data.converter.RawTextESCompositeConverter;
import com.matthewjohnson42.memexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.dto.RawTextSearchRequestDto;
import com.matthewjohnson42.memexService.data.dto.RawTextSearchResponseDto;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.RawTextESComposite;
import com.matthewjohnson42.memexService.data.elasticsearch.repository.RawTextESRestTemplate;
import com.matthewjohnson42.memexService.data.DataService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data service implementing persistence logic for the raw text Elastic Search entity
 */
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

    public Page<RawTextSearchResponseDto> search(RawTextSearchRequestDto rawTextSearchRequestDto) {
        Page<RawTextESComposite> rawTextESPage = rawTextESRestTemplate.getPageFromSearchString(
                rawTextSearchRequestDto.getSearchString(),
                rawTextSearchRequestDto.getStartCreateDate(),
                rawTextSearchRequestDto.getEndCreateDate(),
                rawTextSearchRequestDto.getStartUpdateDate(),
                rawTextSearchRequestDto.getEndUpdateDate(),
                PageRequest.of(rawTextSearchRequestDto.getPageNumber(),
                        rawTextSearchRequestDto.getPageSize(),
                        Sort.by(Sort.Direction.DESC, "_score")));
        List<RawTextSearchResponseDto> rawTextDtos = rawTextESPage.get().map(
                entity -> rawTextESCompositeConverter.convertEntity(entity)).collect(Collectors.toList()
        );
        return new PageImpl<>(rawTextDtos, rawTextESPage.getPageable(), rawTextESPage.getTotalElements());
    }

}
