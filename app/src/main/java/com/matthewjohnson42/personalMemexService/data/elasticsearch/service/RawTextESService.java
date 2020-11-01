package com.matthewjohnson42.personalMemexService.data.elasticsearch.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRepo;
import com.matthewjohnson42.personalMemexService.data.service.DataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RawTextESService extends DataService<RawTextDto, RawTextES> {

    private RawTextESRepo rawTextESRepo;

    public RawTextESService(RawTextESConverter rawTextESConverter, RawTextESRepo rawTextESRepo) {
        this.converter = rawTextESConverter;
        this.rawTextESRepo = rawTextESRepo;
    }

    public Page<RawTextDto> search(String searchString,
                                   LocalDateTime startCreateDate,
                                   LocalDateTime endCreateDate,
                                   LocalDateTime startUpdateDate,
                                   LocalDateTime endUpdateDate,
                                   Pageable pageable) {
//        Page<RawTextES> rawTextESPage = rawTextESRepo.getPageFromSearchString( // non functioning
//                searchString,
//                startCreateDate,
//                endCreateDate,
//                startUpdateDate,
//                endUpdateDate,
//                pageable);
        Page<RawTextES> rawTextESPage = rawTextESRepo.getPageFromSearchString(searchString, pageable);
        List<RawTextDto> rawTextDtos = rawTextESPage.get().map(
                entity -> converter.convertEntity(entity)).collect(Collectors.toList()
        );
        return new PageImpl<>(rawTextDtos, rawTextESPage.getPageable(), rawTextESPage.getTotalElements());
    }

    // pass in date time to allow non-assignment in converter
    public void create(RawTextDto rawTextDto, LocalDateTime createDateTime) {
        RawTextES rawTextES = converter.convertDto(rawTextDto);
        rawTextES.setCreateDateTime(createDateTime);
        rawTextES.setUpdateDateTime(createDateTime);
        rawTextESRepo.save(rawTextES);
    }

    // pass in date time to allow for non-assignment in converter
    public void update(RawTextDto rawTextDto, LocalDateTime updateDateTime) {
        RawTextES rawTextES = getIfExists(rawTextDto.getId());
        rawTextES = converter.updateFromDto(rawTextES, rawTextDto);
        rawTextES.setUpdateDateTime(updateDateTime);
        rawTextESRepo.save(rawTextES);
    }

    public void deleteById(String id) {
        rawTextESRepo.deleteById(id);
    }

    private RawTextES getIfExists(String id) {
        Optional<RawTextES> rawTextEntity = rawTextESRepo.findById(id);
        if (rawTextEntity.isPresent()) {
            return rawTextEntity.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No entity found for id %s", id));
        }
    }

}
