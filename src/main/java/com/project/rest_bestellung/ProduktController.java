package com.project.rest_bestellung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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
    public ArrayList getAllHandleBarTypes() {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/lenkertyp";
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(RESOURCE_URL, ArrayList.class);

        return response.getBody();
    }

    @RequestMapping(value = "/produkt/lenkertyp", method= RequestMethod.GET)
    public ModelAndView showForm(Model model) {
        ArrayList result = getAllHandleBarTypes();
        List<String> allItems = new ArrayList<String>();
        allItems.add((String) result.get(0));
        allItems.add((String) result.get(1));
        allItems.add((String) result.get(2));
        model.addAttribute("allItems", allItems);

        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        // first value will be checked by default.
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForHandlebarType");
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public void processForm(@ModelAttribute(value="checklistForHandlebarType") Checklist checklist) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        System.out.println(checkedItems);
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
