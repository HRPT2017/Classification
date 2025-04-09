package com.fpak.classification.rally.cpr;
import java.util.List;
import com.fpak.classification.rally.cpr.dtos.CPRDriversDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cpr")
@CrossOrigin(origins = "*")

public class CPRController {
    @Value("${cprdrivers.url}")
    private String cprDriversUrl;

    private final CPRService service;

    public CPRController(CPRService service) {
        this.service = service;
    }

    @GetMapping("/drivers")
    public List<CPRDriversDTO> scrapePdfFromUrl() throws Exception {
        return service.getDriversClassification(cprDriversUrl);
    }
}
