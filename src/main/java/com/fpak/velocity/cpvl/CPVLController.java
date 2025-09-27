package com.fpak.velocity.cpvl;

import com.fpak.rally.cpr.CPRService;
import com.fpak.rally.dtos.RallyClassificationDTO;
import com.fpak.velocity.dtos.VelocityClassificationDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/cpvl/classification")
public class CPVLController{
private final CPVLService service;

public CPVLController(CPVLService service) {
    this.service = service;
}

@GetMapping("/absolute")
public List<VelocityClassificationDTO> getAbsDriversClassification() throws Exception {
    return service.getClassification();
}
}
