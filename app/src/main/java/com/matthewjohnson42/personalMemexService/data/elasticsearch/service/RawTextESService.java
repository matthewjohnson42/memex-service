package com.matthewjohnson42.personalMemexService.data.elasticsearch.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRepo;
import com.matthewjohnson42.personalMemexService.data.service.DataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RawTextESService extends DataService<RawTextDto, RawTextES> {

    private RawTextESRepo rawTextESRepo;

    public RawTextESService(RawTextESConverter rawTextESConverter, RawTextESRepo rawTextESRepo) {
        this.converter = rawTextESConverter;
        this.rawTextESRepo = rawTextESRepo;
    }

    public Page<RawTextDto> search(String searchString, Pageable pageable) {
        Page<RawTextES> rawTextESPage = rawTextESRepo.getPageFromSearchString(searchString, pageable);
        List<RawTextDto> rawTextDtos = rawTextESPage.get().map(entity -> converter.convertEntity(entity)).collect(Collectors.toList());
        return new PageImpl<>(rawTextDtos, rawTextESPage.getPageable(), rawTextESPage.getTotalElements());
    }

    public void update(RawTextDto rawTextDto) {
        RawTextES rawTextES = converter.convertDto(rawTextDto);
        rawTextESRepo.save(rawTextES);
    }

    public void deleteById(String id) {
        rawTextESRepo.deleteById(id);
    }

}
