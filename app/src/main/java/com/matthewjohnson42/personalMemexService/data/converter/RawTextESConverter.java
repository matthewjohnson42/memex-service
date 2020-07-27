package com.matthewjohnson42.personalMemexService.data.converter;

import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import org.springframework.stereotype.Component;

@Component
public class RawTextESConverter implements DtoEntityConverter<RawTextDto, RawTextES> {

    public RawTextDto convertEntity(RawTextES rawTextES) {
        return updateFromEntity(new RawTextDto(), rawTextES);
    }

    public RawTextES convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextES(), rawTextDto);
    }

    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextES rawTextES) {
        if (rawTextES.getId() != null) {
            rawTextDto.setId(rawTextES.getId());
        }
        if (rawTextES.getTextContent() != null) {
            rawTextDto.setTextContent(rawTextES.getTextContent());
        }
        return rawTextDto;
    }

    public RawTextES updateFromDto(RawTextES rawTextES, RawTextDto rawTextDto) {
        if (rawTextDto.getId() != null) {
            rawTextES.setId(rawTextDto.getId());
        }
        if (rawTextDto.getTextContent() != null) {
            rawTextES.setTextContent(rawTextDto.getTextContent());
        }
        return rawTextES;
    }

}
