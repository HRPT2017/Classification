package com.fpak.velocity.cpvl;

import com.fpak.rally.RallyService;
import com.fpak.rally.dtos.RallyClassificationDTO;
import com.fpak.velocity.VelocityService;
import com.fpak.velocity.dtos.VelocityClassificationDTO;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class CPVLService {

    private final VelocityService velocityService;

    public CPVLService(VelocityService velocityService) {
        this.velocityService =  velocityService;
    }

    //Read All Pdf files based on Competition Name

    public List<VelocityClassificationDTO> getClassification() throws Exception {
        return velocityService.getClassification("CPV Legends " + Year.now().getValue() + " - Absoluto","abs");
    }

}
