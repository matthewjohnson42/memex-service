package com.matthewjohnson42.personalMemexService.data.elasticsearch.service;

import com.matthewjohnson42.personalMemexService.data.converter.RawTextESConverter;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.entity.RawTextES;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.repository.RawTextESRestTemplate;
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

    private RawTextESRestTemplate elasticSearchRestTemplate;

    public RawTextESService(RawTextESConverter rawTextESConverter,
                            RawTextESRestTemplate elasticSearchRestTemplate) {
        this.converter = rawTextESConverter;
        this.elasticSearchRestTemplate = elasticSearchRestTemplate;
    }

    public Page<RawTextDto> search(String searchString,
                                   LocalDateTime startCreateDate,
                                   LocalDateTime endCreateDate,
                                   LocalDateTime startUpdateDate,
                                   LocalDateTime endUpdateDate,
                                   Pageable pageable) {
        Page<RawTextES> rawTextESPage = elasticSearchRestTemplate.getPageFromSearchString(
                searchString,
                startCreateDate,
                endCreateDate,
                startUpdateDate,
                endUpdateDate,
                pageable);
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
        elasticSearchRestTemplate.save(rawTextES);
    }

    // pass in date time to allow for non-assignment in converter
    public void update(RawTextDto rawTextDto, LocalDateTime updateDateTime) {
        Optional<RawTextES> rawTextESOpt = elasticSearchRestTemplate.findById(rawTextDto.getId());
        RawTextES rawTextES = rawTextESOpt.isPresent() ?
                converter.updateFromDto(rawTextESOpt.get(), rawTextDto) :
                converter.convertDto(rawTextDto);
        rawTextES.setUpdateDateTime(updateDateTime);
        elasticSearchRestTemplate.save(rawTextES);
    }

    public void deleteById(String id) {
        elasticSearchRestTemplate.deleteById(id);
    }

    public RawTextDto get(String id) {
        Optional<RawTextES> rawTextOptional = elasticSearchRestTemplate.findById(id);
        if (rawTextOptional.isPresent()) {
            return converter.convertEntity(rawTextOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No entity found for id %s", id));
        }
    }

}
