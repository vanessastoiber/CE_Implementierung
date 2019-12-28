package com.project.rest_bestellung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Controller to test RestTemplate configuration
 */
@RestController
public class ProduktController {

    private RestTemplate restTemplate;

    @Autowired
    public ProduktController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @RequestMapping("/produkt/griff")
    public String getAllHandles() {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/griff";
        ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL, String.class);

        return response.getBody();
    }

    @RequestMapping("/produkt/lenkertyp")
    public String getAllHandleBarTypes() {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/lenkertyp";
        ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL, String.class);

        return response.getBody();
    }

    @RequestMapping("/produkt/material")
    public String getAllMaterial() {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/material";
        ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL, String.class);

        return response.getBody();
    }

    @RequestMapping("/produkt/schaltung")
    public String getAllGearLevels() {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/schaltung";
        ResponseEntity<String> response = restTemplate.getForEntity(RESOURCE_URL, String.class);

        return response.getBody();
    }
}
