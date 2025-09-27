package com.fpak.rally.cpmr;
import java.util.List;

import com.fpak.rally.dtos.RallyClassificationDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cpmr/classification")
public class CPMRController {
     private final CPMRService service;

    public CPMRController(CPMRService service) {
        this.service = service;
    }

    @GetMapping("/absolute/drivers")
    public List<RallyClassificationDTO> getAbsDriversClassification() throws Exception {
        return service.getAbsDriversClassification();
    }

    @GetMapping("/absolute/codrivers")
    public List<RallyClassificationDTO> getAbsCoDriversClassification() throws Exception {
        return service.getAbsCoDriversClassification();
    }

}
