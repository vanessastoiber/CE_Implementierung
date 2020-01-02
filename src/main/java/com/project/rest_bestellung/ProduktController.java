package com.project.rest_bestellung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to test RestTemplate configuration
 */
@Controller
public class ProduktController {

    private RestTemplate restTemplate;
    
    private Order order = new Order();

    @Autowired
    public ProduktController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    
    @RequestMapping("/produkt/griff")
    public ArrayList getAllHandles(String url) {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/schaltung";
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);

        return response.getBody();
    }
    
    @RequestMapping(value = "/produkt/griff", method= RequestMethod.GET)
    public ModelAndView showFormHandles(Model model) {
    	String url = "https://www.maripavi.at/produkt/griff?material=" + order.getMaterial();
    	ArrayList result = getAllHandles(url);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForHandles");
    }
    
    @RequestMapping(value = "/processHandleForm", method= RequestMethod.POST)
    public ModelAndView processHandleForm(@ModelAttribute(value="checklistForHandle") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        System.out.println("ausgewählter Griff: " + checkedItems);
        order.setHandle(checkedItems.get(0));
        System.out.println(order.toString());
        
        model.addAttribute("handlebartype", order.getHandlebartype());
        model.addAttribute("material", order.getMaterial());
        model.addAttribute("gearLevel", order.getGearLevels());
        model.addAttribute("handle", order.getHandle());
        return new ModelAndView("order");
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
    public ModelAndView processForm(@ModelAttribute(value="checklistForHandlebarType") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        order.setHandlebartype(checkedItems.get(0));
        System.out.println(order.toString());
        return showFormMaterial(model);
    }

    @RequestMapping("/produkt/material")
    public ArrayList getAllMaterial(String url) {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/material";
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);

        return response.getBody();
    }
    
    @RequestMapping(value = "/produkt/material", method= RequestMethod.GET)
    public ModelAndView showFormMaterial(Model model) {
    	String url = "https://www.maripavi.at/produkt/material?lenkertyp=" + order.getHandlebartype();
    	ArrayList result = getAllMaterial(url);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForMaterial");
    }
    
    @RequestMapping(value = "/processMaterialForm", method= RequestMethod.POST)
    public ModelAndView processMaterialForm(@ModelAttribute(value="checklistForMaterial") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        System.out.println("ausgewählter Lenkertyp: " + checkedItems);
        order.setMaterial(checkedItems.get(0));
        System.out.println(order.toString());
        return showFormGearLevel(model);
    }
    
    @RequestMapping("/produkt/schaltung")
    public ArrayList getAllGearLevels(String url) {
        final String RESOURCE_URL = "https://www.maripavi.at/produkt/schaltung";
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);

        return response.getBody();
    }
    
    @RequestMapping(value = "/produkt/schaltung", method= RequestMethod.GET)
    public ModelAndView showFormGearLevel(Model model) {
    	String url = "https://www.maripavi.at/produkt/schaltung?lenkertyp=" + order.getHandlebartype();
    	ArrayList result = getAllGearLevels(url);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForGearLevels");
    }
    
    @RequestMapping(value = "/processGearLevelForm", method= RequestMethod.POST)
    public ModelAndView processGearLevelForm(@ModelAttribute(value="checklistForGearLevels") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        System.out.println("ausgewählte Schaltung: " + checkedItems);
        order.setGearLevels(checkedItems.get(0));
        System.out.println(order.toString());
        return showFormHandles(model);
    }
}
