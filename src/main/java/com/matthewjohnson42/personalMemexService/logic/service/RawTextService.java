package com.matthewjohnson42.personalMemexService.logic.service;

import com.matthewjohnson42.personalMemexService.data.dto.RawTextDTO;
import com.matthewjohnson42.personalMemexService.data.dto.RawTextSummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RawTextService {

    Logger logger = LoggerFactory.getLogger(RawTextService.class);

    public List<RawTextSummaryDTO> getSummaries() {
        List<RawTextSummaryDTO> list = new ArrayList<RawTextSummaryDTO>();
        list.add(new RawTextSummaryDTO("here"));
        return list;
    }

    public RawTextDTO getById(String id) {
        return new RawTextDTO(id);
    }

    public void create(RawTextDTO dto) {

    }

    public void deleteById(String id) {

    }

}
