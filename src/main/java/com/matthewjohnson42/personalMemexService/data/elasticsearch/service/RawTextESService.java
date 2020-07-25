package com.matthewjohnson42.personalMemexService.data.elasticsearch.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRepo;
import com.matthewjohnson42.personalMemexService.data.service.DataService;
import org.springframework.stereotype.Service;

@Service
public class RawTextESService extends DataService<RawTextDto, RawTextES> {

    private RawTextESRepo rawTextESRepo;

    public RawTextESService(RawTextESConverter rawTextESConverter, RawTextESRepo rawTextESRepo) {
        this.converter = rawTextESConverter;
        this.rawTextESRepo = rawTextESRepo;
    }

    public void update(RawTextDto rawTextDto) {
        RawTextES rawTextES = converter.convertDto(rawTextDto);
        rawTextESRepo.save(rawTextES);
    }

    public void deleteById(String id) {
        rawTextESRepo.deleteById(id);
    }

}
