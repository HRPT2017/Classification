package com.fpak.rally.cpmr;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;
import com.fpak.rally.dtos.CPRAbsDriversDTO;

@Service
public class CPMRService {
    private final RallyService rallyService;

    public CPMRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    //Read All Pdf files based on Competition Name

    public List<CPRAbsDriversDTO> getAbsDriversClassification() throws Exception {
        return rallyService.getAbsClassification("C P Masters Ralis " + String.valueOf(Year.now().getValue()) + " Condutores - Absoluto");
    }

    public List<CPRAbsDriversDTO> getAbsCoDriversClassification() throws Exception {
        return rallyService.getAbsClassification("C P Masters Ralis " + String.valueOf(Year.now().getValue()) + " Navegadores - Absoluto");
    }

}
