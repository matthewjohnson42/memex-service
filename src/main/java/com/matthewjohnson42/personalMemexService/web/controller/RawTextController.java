package com.matthewjohnson42.personalMemexService.web.controller;

import com.matthewjohnson42.personalMemexService.data.dto.IdListDto;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDto;
import com.matthewjohnson42.personalMemexService.logic.service.RawTextService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web server http request controller for the RawText entity type
 */
@RestController
@RequestMapping("/api/v0/rawText")
public class RawTextController {

    private RawTextService rawTextService;

    public RawTextController(RawTextService rawTextService) {
        this.rawTextService = rawTextService;
    }

    @GetMapping("/summaries")
    public IdListDto getAllSummaries() {
        return rawTextService.getSummaries();
    }

    @GetMapping("/{id}")
    public RawTextDto get(@PathVariable String id) {
        return rawTextService.getById(id);
    }

    @PostMapping
    public RawTextDto create(@Validated @RequestBody RawTextDto dto) {
        return rawTextService.create(dto);
    }

    @PutMapping("/{id}")
    public RawTextDto update(@PathVariable String id, @Validated @RequestBody RawTextDto dto) {
        return rawTextService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rawTextService.deleteById(id);
    }

    @DeleteMapping
    public void deleteMany(@Validated @RequestBody IdListDto idListDto) {
        rawTextService.deleteMany(idListDto);
    }

}
