package com.fpak.rally.cpr;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.fpak.rally.dtos.CPRAbsDriversDTO;
import com.fpak.rally.dtos.CPRDriversDTO;


@RestController
@RequestMapping("/api/cpr/classification")
public class CPRController {

    private final CPRService service;

    public CPRController(CPRService service) {
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

    @GetMapping("/rally2/drivers")
    public List<CPRDriversDTO> getRallyDriversClassification() throws Exception {
        return service.getRally2DriversClassification();
    }

    @GetMapping("/rally2/co-drivers")
    public List<CPRDriversDTO> getRally2CoDriversClassification() throws Exception {
        return service.getRally2CoDriversClassification();
    }

}
