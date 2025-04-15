package com.fpak.rally.cp2rm;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;
import com.fpak.rally.dtos.CPRAbsDriversDTO;
import com.fpak.rally.dtos.CPRDriversDTO;


@Service
public class CP2RMService {
      private final RallyService rallyService;

    public CP2RMService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    public List<CPRAbsDriversDTO> getCP2RMDriversClassification()throws Exception{
        return  rallyService.getAbsClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Condutoress - Absoluto");
    }

    public List<CPRAbsDriversDTO> getCP2RMCoDriversClassification()throws Exception{
        return  rallyService.getAbsClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Navegadores - Absoluto");
    }

    public List<CPRDriversDTO> getRC4DriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Condutoress - RC4");
    }

    public List<CPRDriversDTO> getRC4CoDriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Navegadores - RC4");
    }

    public List<CPRDriversDTO> getRC5DriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Condutoress - RC5");
    }

    public List<CPRDriversDTO> getRC5CoDriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis 2RM " + String.valueOf(Year.now().getValue()) + " Navegadores - RC5");
    }
}
