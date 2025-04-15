package com.fpak.rally.cpmr;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.fpak.rally.dtos.CPRAbsDriversDTO;


@RestController
@RequestMapping("/api/cpmr/classification")
public class CPMRController {
     private final CPMRService service;

    public CPMRController(CPMRService service) {
        this.service = service;
    }

    @GetMapping("/absolute/drivers")
    public List<CPRAbsDriversDTO> getAbsDriversClassification() throws Exception {
        return service.getAbsDriversClassification();
    }

    @GetMapping("/absolute/codrivers")
    public List<CPRAbsDriversDTO> getAbsCoDriversClassification() throws Exception {
        return service.getAbsCoDriversClassification();
    }

}
