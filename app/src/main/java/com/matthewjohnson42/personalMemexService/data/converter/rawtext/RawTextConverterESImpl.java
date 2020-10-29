package com.matthewjohnson42.personalMemexService.data.converter.rawtext;

import com.matthewjohnson42.personalMemexService.data.converter.IDtoEntityConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.persistence.elasticsearch.entity.RawTextES;
import org.springframework.stereotype.Component;

/**
 * Class providing entity typing for the AbstractRawTextConverter class and implementation of the DtoEntityConverter interface
 */
@Component
public class RawTextConverterESImpl implements IDtoEntityConverter<RawTextDto, RawTextES> {

    public RawTextDto convertEntity(RawTextES rawTextES) {
        return updateFromEntity(new RawTextDto(), rawTextES);
    }

    public RawTextES convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextES(), rawTextDto);
    }

    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextES rawTextEntity) {
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

    public RawTextES updateFromDto(RawTextES rawTextEntity, RawTextDto rawTextDto) {
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
