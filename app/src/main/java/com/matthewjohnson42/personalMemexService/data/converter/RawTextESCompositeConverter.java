package com.matthewjohnson42.personalMemexService.data.converter;

import com.matthewjohnson42.personalMemexService.data.DtoEntityConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextSearchResponseDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextESComposite;
import org.springframework.stereotype.Component;

@Component
public class RawTextESCompositeConverter implements DtoEntityConverter<String, RawTextSearchResponseDto, RawTextESComposite> {

    public RawTextSearchResponseDto convertEntity(RawTextESComposite rawTextESComposite) {
        return updateFromEntity(new RawTextSearchResponseDto(), rawTextESComposite);
    }

    public RawTextESComposite convertDto(RawTextSearchResponseDto rawTextSearchResponseDto) {
        return updateFromDto(new RawTextESComposite(), rawTextSearchResponseDto);
    }

    public RawTextSearchResponseDto updateFromEntity(RawTextSearchResponseDto rawTextSearchResponseDto, RawTextESComposite rawTextESComposite) {
        rawTextSearchResponseDto = DtoEntityConverter.super.updateFromEntity(rawTextSearchResponseDto, rawTextESComposite);
        if (rawTextESComposite.getTextContent() != null) {
            rawTextSearchResponseDto.setTextContent(rawTextESComposite.getTextContent());
        }
        if (rawTextESComposite.getHighlights() != null) {
            rawTextSearchResponseDto.setHighlights(rawTextESComposite.getHighlights());
        }
        return rawTextSearchResponseDto;
    }

    public RawTextESComposite updateFromDto(RawTextESComposite rawTextESComposite, RawTextSearchResponseDto rawTextSearchResponseDto) {
        rawTextESComposite = DtoEntityConverter.super.updateFromDto(rawTextESComposite, rawTextSearchResponseDto);
        if (rawTextSearchResponseDto.getTextContent() != null) {
            rawTextESComposite.setTextContent(rawTextSearchResponseDto.getTextContent());
        }
        if (rawTextSearchResponseDto.getHighlights() != null) {
            rawTextESComposite.setHighlights(rawTextSearchResponseDto.getHighlights());
        }
        return rawTextESComposite;
    }

}
