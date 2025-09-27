package com.fpak.rally.cp2rm;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fpak.rally.RallyService;

import com.fpak.rally.dtos.RallyClassificationDTO;


@Service
public class CP2RMService {
      private final RallyService rallyService;

    public CP2RMService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    public List<RallyClassificationDTO> getCP2RMDriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue() + " Condutores - Absoluto", "abs");
    }

    public List<RallyClassificationDTO> getCP2RMCoDriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue()+ " Navegadores - Absoluto", "abs");
    }

    public List<RallyClassificationDTO> getRC4DriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue() + " Condutores - RC4","normal");
    }

    public List<RallyClassificationDTO> getRC4CoDriversClassification()throws Exception{
        return  rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue() + " Navegadores - RC4","normal");
    }

    public List<RallyClassificationDTO> getRC5DriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue() + " Condutores - RC5","normal");
    }

    public List<RallyClassificationDTO> getRC5CoDriversClassification()throws Exception{
        return rallyService.getClassification("C P Ralis 2RM " + Year.now().getValue() + " Navegadores - RC5","normal");
    }
}

