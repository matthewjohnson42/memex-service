package com.matthewjohnson42.memexService.data.converter;

import com.matthewjohnson42.memexService.data.DtoEntityConverter;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.mongo.entity.RawTextMongo;
import org.springframework.stereotype.Component;

/**
 * Entity/DTO converter for raw text Mongo data
 */
@Component
public class RawTextMongoConverter implements DtoEntityConverter<String, RawTextDto, RawTextMongo> {

    public RawTextDto convertEntity(RawTextMongo rawTextMongo) {
        return updateFromEntity(new RawTextDto(), rawTextMongo);
    }

    public RawTextMongo convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextMongo(), rawTextDto);
    }

    @Override
    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextMongo rawTextMongo) {
        rawTextDto = DtoEntityConverter.super.updateFromEntity(rawTextDto, rawTextMongo);
        if (rawTextMongo.getTextContent() != null) {
            rawTextDto.setTextContent(rawTextMongo.getTextContent());
        }
        return rawTextDto;
    }

    public RawTextMongo updateFromDto(RawTextMongo rawTextMongo, RawTextDto rawTextDto) {
        rawTextMongo = DtoEntityConverter.super.updateFromDto(rawTextMongo, rawTextDto);
        if (rawTextDto.getTextContent() != null) {
            rawTextMongo.setTextContent(rawTextDto.getTextContent());
        }
        return rawTextMongo;
    }

}
