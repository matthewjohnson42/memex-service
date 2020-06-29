package com.matthewjohnson42.personalMemexService.web.controller;

import com.matthewjohnson42.personalMemexService.data.dto.IdListDTO;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextDTO;
import com.matthewjohnson42.personalMemexService.data.service.RawTextService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public IdListDTO getAllSummaries() {
        return rawTextService.getSummaries();
    }

    @GetMapping("/{id}")
    public RawTextDTO get(@PathVariable String id) {
        return rawTextService.getById(id);
    }

    @PostMapping
    public RawTextDTO create(@Validated @RequestBody RawTextDTO dto) {
        return rawTextService.create(dto);
    }

    @PostMapping("/{id}")
    public RawTextDTO update(@PathVariable String id, @Validated @RequestBody RawTextDTO dto) {
        dto.setId(id);
        return rawTextService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rawTextService.deleteById(id);
    }

}
