package com.fpak.rally.cp2rm;

import java.util.List;

import com.fpak.rally.dtos.RallyClassificationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/cp2rm/classification")
public class CP2RMController {
    
    private final CP2RMService service;

    public CP2RMController(CP2RMService service) {
        this.service = service;
    }
    
    @GetMapping("/absolute/drivers")
    public List<RallyClassificationDTO> getCP2RMDriversClassification() throws Exception {
        return service.getCP2RMDriversClassification();
    }

    @GetMapping("/absolute/co-drivers")
    public List<RallyClassificationDTO> getCP2RMCoDriversClassification() throws Exception {
        return service.getCP2RMCoDriversClassification();
    }
    
    @GetMapping("/rc4/drivers")
    public List<RallyClassificationDTO> getRC4DriversClassification() throws Exception {
        return service.getRC4DriversClassification();
    }

    @GetMapping("/rc4/co-drivers")
    public List<RallyClassificationDTO> getRC4CoDriversClassification() throws Exception {
        return service.getRC4CoDriversClassification();
    }

    @GetMapping("/rc5/drivers")
    public List<RallyClassificationDTO> getRC5DriversClassification() throws Exception {
        return service.getRC5DriversClassification();
    }

    @GetMapping("/rc5/co-drivers")
    public List<RallyClassificationDTO> getRC5CoDriversClassification() throws Exception {
        return service.getRC5CoDriversClassification();
    }
}
