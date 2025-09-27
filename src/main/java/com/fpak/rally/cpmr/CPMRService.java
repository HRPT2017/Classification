package com.fpak.rally.cpmr;

import java.time.Year;
import java.util.List;
import com.fpak.rally.dtos.RallyClassificationDTO;

import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;


@Service
public class CPMRService {
    private final RallyService rallyService;

    public CPMRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    //Read All Pdf files based on Competition Name

    public List<RallyClassificationDTO> getAbsDriversClassification() throws Exception {
        return rallyService.getClassification("C P Masters Ralis " + Year.now().getValue() + " Condutores - Absoluto","abs");
    }

    public List<RallyClassificationDTO> getAbsCoDriversClassification() throws Exception {
        return rallyService.getClassification("C P Masters Ralis " + Year.now().getValue() + " Navegadores - Absoluto","abs");
    }

}
