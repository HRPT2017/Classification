package com.fpak.classification.rally.cpr;
import java.util.List;
import com.fpak.classification.rally.cpr.dtos.CPRDriversDTO;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cpr")
public class CPRController {

    private final CPRService service;

    public CPRController(CPRService service) {
        this.service = service;
    }

    @GetMapping("/drivers")
    public List<CPRDriversDTO> getDriversClassificationg() throws Exception {
        return service.getDriversClassification();
    }

    @GetMapping("/codrivers")
    public List<CPRDriversDTO> getCoDriversClassificationg() throws Exception {
        return service.getCoDriversClassification();
    }


}
