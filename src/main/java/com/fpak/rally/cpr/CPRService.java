package com.fpak.rally.cpr;
import java.util.List;
import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;
import com.fpak.rally.dtos.CPRAbsDriversDTO;
import com.fpak.rally.dtos.CPRDriversDTO;

import java.time.Year;


@Service
public class CPRService {
    private final RallyService rallyService;

    public CPRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    //Read All Pdf files based on Competition Name

    public List<CPRAbsDriversDTO> getAbsDriversClassification() throws Exception {
        return rallyService.getAbsClassification("C P Ralis " + String.valueOf(Year.now().getValue()) + " Condutores - Absoluto");
    }

    public List<CPRAbsDriversDTO> getAbsCoDriversClassification() throws Exception {
        return rallyService.getAbsClassification("C P Ralis " + String.valueOf(Year.now().getValue()) + " Navegadores - Absoluto");
    }

    public List<CPRDriversDTO> getRally2DriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis " + String.valueOf(Year.now().getValue()) + " Condutoress - Rally 2");
    }

    public List<CPRDriversDTO> getRally2CoDriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis " + String.valueOf(Year.now().getValue()) + " Navegadores - Rally 2");
    }

}
