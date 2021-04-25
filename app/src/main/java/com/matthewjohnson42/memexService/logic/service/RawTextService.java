package com.matthewjohnson42.memexService.logic.service;

import com.matthewjohnson42.memexService.data.dto.IdListDto;
import com.matthewjohnson42.memexService.data.dto.PageRequestDto;
import com.matthewjohnson42.memexService.data.dto.RawTextDto;
import com.matthewjohnson42.memexService.data.dto.RawTextSearchRequestDto;
import com.matthewjohnson42.memexService.data.dto.RawTextSearchResponseDto;
import com.matthewjohnson42.memexService.data.elasticsearch.service.RawTextESService;
import com.matthewjohnson42.memexService.data.mongo.service.RawTextMongoService;
import com.matthewjohnson42.memexService.logic.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

// todo make generic in the type of the DTO being returned
/**
 * Service implementing raw text functions that operate across repository types.
 * Intended interface for DataServices.
 */
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
        boolean validId = false;
        while (!validId) {
            String prospectiveId = StringUtils.randomId();
            if (!rawTextMongoService.exists(prospectiveId)) {
                rawTextDto.setId(prospectiveId);
                validId = true;
            }
        }
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

    public Page<RawTextSearchResponseDto> search(RawTextSearchRequestDto rawTextSearchDto) {
        return rawTextESService.search(rawTextSearchDto);
    }

}
