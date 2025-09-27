package com.fpak.rally.cpr;
import java.util.List;

import com.fpak.rally.dtos.RallyClassificationDTO;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/cpr/classification")
public class CPRController {

    private final CPRService service;

    public CPRController(CPRService service) {
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

    @GetMapping("/rally2/drivers")
    public List<RallyClassificationDTO> getRallyDriversClassification() throws Exception {
        return service.getRally2DriversClassification();
    }

    @GetMapping("/rally2/co-drivers")
    public List<RallyClassificationDTO> getRally2CoDriversClassification() throws Exception {
        return service.getRally2CoDriversClassification();
    }

}
