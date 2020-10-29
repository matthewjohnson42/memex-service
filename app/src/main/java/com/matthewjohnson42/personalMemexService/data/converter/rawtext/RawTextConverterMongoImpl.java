package com.matthewjohnson42.personalMemexService.data.converter.rawtext;

import com.matthewjohnson42.personalMemexService.data.converter.IDtoEntityConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.persistence.mongo.entity.RawTextMongo;
import org.springframework.stereotype.Component;

/**
 * Conversion class for dtos and entities of the mongo persistence provider
 */
@Component
public class RawTextConverterMongoImpl implements IDtoEntityConverter<RawTextDto, RawTextMongo> {

    public RawTextDto convertEntity(RawTextMongo rawTextMongo) {
        return updateFromEntity(new RawTextDto(), rawTextMongo);
    }

    public RawTextMongo convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextMongo(), rawTextDto);
    }

    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextMongo rawTextEntity) {
        if (rawTextEntity.getId() != null) {
            rawTextDto.setId(rawTextEntity.getId());
        }
        if (rawTextEntity.getTextContent() != null) {
            rawTextDto.setTextContent(rawTextEntity.getTextContent());
        }
        if (rawTextEntity.getCreateDateTime() != null) {
            rawTextDto.setCreateDatetime(rawTextEntity.getCreateDateTime());
        }
        if (rawTextEntity.getUpdateDateTime() != null) {
            rawTextDto.setUpdateDatetime(rawTextEntity.getUpdateDateTime());
        }
        return rawTextDto;
    }

    public RawTextMongo updateFromDto(RawTextMongo rawTextEntity, RawTextDto rawTextDto) {
        if (rawTextDto.getId() != null) {
            rawTextEntity.setId(rawTextDto.getId());
        }
        if (rawTextDto.getTextContent() != null) {
            rawTextEntity.setTextContent(rawTextDto.getTextContent());
        }
        if (rawTextDto.getCreateDateTime() != null) {
            rawTextEntity.setCreateDateTime(rawTextDto.getCreateDateTime());
        }
        if (rawTextDto.getUpdateDateTime() != null) {
            rawTextEntity.setUpdateDateTime(rawTextDto.getUpdateDateTime());
        }
        return rawTextEntity;
    }

}
