package com.matthewjohnson42.personalMemexService.data.converter;

import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import org.springframework.stereotype.Component;

@Component
public class RawTextMongoConverter implements DtoEntityConverter<RawTextDto, RawTextMongo> {

    public RawTextDto convertEntity(RawTextMongo rawTextMongo) {
        return updateFromEntity(new RawTextDto(), rawTextMongo);
    }

    public RawTextMongo convertDto(RawTextDto rawTextDto) {
        return updateFromDto(new RawTextMongo(), rawTextDto);
    }

    public RawTextDto updateFromEntity(RawTextDto rawTextDto, RawTextMongo rawTextMongo) {
        if (rawTextMongo.getId() != null) {
            rawTextDto.setId(rawTextMongo.getId());
        }
        if (rawTextMongo.getText() != null) {
            rawTextDto.setText(rawTextMongo.getText());
        }
        return rawTextDto;
    }

    public RawTextMongo updateFromDto(RawTextMongo rawTextMongo, RawTextDto rawTextDto) {
        if (rawTextDto.getId() != null) {
            rawTextMongo.setId(rawTextDto.getId());
        }
        if (rawTextDto.getText() != null) {
            rawTextMongo.setText(rawTextDto.getText());
        }
        return rawTextMongo;
    }

}
