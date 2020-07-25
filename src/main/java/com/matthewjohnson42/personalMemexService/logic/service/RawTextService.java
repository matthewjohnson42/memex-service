package com.matthewjohnson42.personalMemexService.logic.service;

import com.matthewjohnson42.personalMemexService.data.dto.IdListDto;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.service.RawTextESService;
import com.matthewjohnson42.personalMemexService.data.mongo.entity.RawTextMongo;
import com.matthewjohnson42.personalMemexService.data.mongo.service.RawTextMongoService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RawTextService {

    private RawTextESService rawTextESService;
    private RawTextMongoService rawTextMongoService;

    public RawTextService(RawTextESService rawTextESService, RawTextMongoService rawTextMongoService) {
        this.rawTextESService = rawTextESService;
        this.rawTextMongoService = rawTextMongoService;
    }

    public IdListDto getSummaries() {
        List<RawTextMongo> rawTextMongoList = rawTextMongoService.getSummaries();
        IdListDto idListDTO = new IdListDto();
        rawTextMongoList.forEach(e -> {
            idListDTO.addId(e.getId());
        });
        return idListDTO;
    }

    public RawTextDto getById(String id) {
        return rawTextMongoService.getById(id);
    }

    public RawTextDto create(RawTextDto rawTextDto) {
        rawTextDto = rawTextMongoService.create(rawTextDto);
        rawTextESService.update(rawTextDto);
        return rawTextDto;
    }

    public RawTextDto update(String id, RawTextDto rawTextDto) {
        rawTextDto.setId(id);
        rawTextDto = rawTextMongoService.update(rawTextDto);
        rawTextESService.update(rawTextDto);
        return rawTextDto;
    }

    public RawTextDto deleteById(String id) {
        RawTextDto rawTextDto = rawTextMongoService.deleteById(id);
        rawTextESService.deleteById(id);
        return rawTextDto;
    }

    public void deleteMany(IdListDto idListDto) {
        idListDto.getIds().stream().forEach(
                id -> {
                    try {
                        rawTextMongoService.deleteById(id);
                        rawTextESService.deleteById(id);
                    } catch (ResponseStatusException e) { /* no op */ }
                });
    }

}
