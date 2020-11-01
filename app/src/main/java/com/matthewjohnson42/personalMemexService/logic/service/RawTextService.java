package com.matthewjohnson42.personalMemexService.logic.service;

import com.matthewjohnson42.personalMemexService.data.dto.IdListDto;
import com.matthewjohnson42.personalMemexService.data.dto.PageRequestDto;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextSearchDto;
import com.matthewjohnson42.personalMemexService.data.elasticsearch.service.RawTextESService;
import com.matthewjohnson42.personalMemexService.data.mongo.service.RawTextMongoService;
import com.matthewjohnson42.personalMemexService.logic.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class RawTextService {

    private RawTextESService rawTextESService;
    private RawTextMongoService rawTextMongoService;

    public RawTextService(RawTextESService rawTextESService, RawTextMongoService rawTextMongoService) {
        this.rawTextESService = rawTextESService;
        this.rawTextMongoService = rawTextMongoService;
    }

    public RawTextDto create(RawTextDto rawTextDto) {
        LocalDateTime createDateTime = LocalDateTime.now();
        rawTextDto.setId(StringUtils.randomId());
        rawTextDto = rawTextMongoService.create(rawTextDto, createDateTime);
        rawTextESService.create(rawTextDto, createDateTime);
        return rawTextDto;
    }

    public RawTextDto getById(String id) {
        return rawTextMongoService.getById(id);
    }

    public RawTextDto update(String id, RawTextDto rawTextDto) {
        LocalDateTime updateDateTime = LocalDateTime.now();
        rawTextDto.setId(id);
        rawTextDto = rawTextMongoService.update(rawTextDto, updateDateTime);
        rawTextESService.update(rawTextDto, updateDateTime);
        return rawTextDto;
    }

    public RawTextDto deleteById(String id) {
        RawTextDto rawTextDto = rawTextMongoService.deleteById(id);
        rawTextESService.deleteById(id);
        return rawTextDto;
    }

    public Page<RawTextDto> getPage(PageRequestDto pageRequestDto) {
        return rawTextMongoService.getPage(
                PageRequest.of(pageRequestDto.getPageNumber(),
                pageRequestDto.getPageSize(),
                Sort.by(Sort.Direction.fromString(pageRequestDto.getSort()), "_id")));
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

    public Page<RawTextDto> search(RawTextSearchDto rawTextSearchDto) {
        return rawTextESService.search(
                rawTextSearchDto.getSearchString(),
                rawTextSearchDto.getStartCreateDate(),
                rawTextSearchDto.getEndCreateDate(),
                rawTextSearchDto.getStartUpdateDate(),
                rawTextSearchDto.getEndUpdateDate(),
                PageRequest.of(rawTextSearchDto.getPageNumber(),
                        rawTextSearchDto.getPageSize(),
                        Sort.by(Sort.Direction.DESC, "_score")));
    }

}
