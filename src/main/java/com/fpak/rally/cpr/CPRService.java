package com.fpak.rally.cpr;
import java.util.List;

import com.fpak.rally.dtos.RallyClassificationDTO;
import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;

import java.time.Year;


@Service
public class CPRService {
    private final RallyService rallyService;

    public CPRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    //Read All Pdf files based on Competition Name

    public List<RallyClassificationDTO> getAbsDriversClassification() throws Exception {
        return rallyService.getClassification("C P Ralis " + Year.now().getValue() + " Condutores - Absoluto","abs");
    }

    public List<RallyClassificationDTO> getAbsCoDriversClassification() throws Exception {
        return rallyService.getClassification("C P Ralis " + Year.now().getValue() + " Navegadores - Absoluto","abs");
    }

    public List<RallyClassificationDTO> getRally2DriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis " + Year.now().getValue() + " Condutores - Rally 2","normal");
    }

    public List<RallyClassificationDTO> getRally2CoDriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis " + Year.now().getValue() + " Navegadores - Rally 2","normal");
    }

}
