package com.matthewjohnson42.memexService.data.converter;

import com.matthewjohnson42.memexService.data.DtoEntityConverter;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.elasticsearch.entity.RawTextES;
import org.springframework.stereotype.Component;

/**
 * Entity/DTO converter for raw text Elastic Search data
 */
@Component
public class RawTextESConverter implements DtoEntityConverter<String, RawTextDto, RawTextES> {

    public RawTextDto convertEntity(RawTextES rawTextES) {
        return updateFromEntity(new RawTextDto(), rawTextES);
    }

    public RawTextES convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextES(), rawTextDto);
    }

    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextES rawTextES) {
        rawTextDto = DtoEntityConverter.super.updateFromEntity(rawTextDto, rawTextES);
        if (rawTextES.getTextContent() != null) {
            rawTextDto.setTextContent(rawTextES.getTextContent());
        }
        return rawTextDto;
    }

    public RawTextES updateFromDto(RawTextES rawTextES, RawTextDto rawTextDto) {
        rawTextES = DtoEntityConverter.super.updateFromDto(rawTextES, rawTextDto);
        if (rawTextDto.getTextContent() != null) {
            rawTextES.setTextContent(rawTextDto.getTextContent());
        }
        return rawTextES;
    }

}