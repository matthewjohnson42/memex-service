package com.matthewjohnson42.personalMemexService.web.controller;

import com.matthewjohnson42.personalMemexService.data.dto.RawTextDTO;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextSummaryDTO;
import com.matthewjohnson42.personalMemexService.logic.service.RawTextService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0/rawText")
public class RawTextController {

    private RawTextService rawTextService;

    public RawTextController(RawTextService rawTextService) {
        this.rawTextService = rawTextService;
    }

    @GetMapping("/summaries")
    public List<RawTextSummaryDTO> getAllSummaries() {
        return rawTextService.getSummaries();
    }

    @GetMapping("/{id}")
    public RawTextDTO get(@PathVariable String id) {
        return rawTextService.getById(id);
    }

    @PostMapping
    public void post(@Validated @RequestBody RawTextDTO dto) {
        rawTextService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        rawTextService.deleteById(id);
    }

}
