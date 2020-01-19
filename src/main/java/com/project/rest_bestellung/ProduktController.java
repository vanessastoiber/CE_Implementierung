package com.project.rest_bestellung;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public ArrayList getAllOptions(String path) {
    	String url = "https://www.maripavi.at/produkt/" + path;
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);

        return response.getBody();
    }
    
    @RequestMapping(value = "/produkt/lenkertyp", method= RequestMethod.GET)
    public ModelAndView showForm(Model model) {
    	String path = "lenkertyp";
        ArrayList result = getAllOptions(path);
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
    public String processForm(@ModelAttribute(value="checklistForHandlebarType") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        order.setHandlebartype(checkedItems.get(0));
        System.out.println(order.toString());
        return "redirect:/produkt/material";
    }

    @RequestMapping(value = "/produkt/material", method= RequestMethod.GET)
    public ModelAndView showFormMaterial(Model model) {
    	String path = "material?lenkertyp=" + order.getHandlebartype();
    	ArrayList result = getAllOptions(path);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForMaterial");
    }
    
    @RequestMapping(value = "/processMaterialForm", method= RequestMethod.POST)
    public String processMaterialForm(@ModelAttribute(value="checklistForMaterial") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        order.setMaterial(checkedItems.get(0));
        System.out.println(order.toString());
        return "redirect:/produkt/schaltung";
    }
    
    @RequestMapping(value = "/produkt/schaltung", method= RequestMethod.GET)
    public ModelAndView showFormGearLevel(Model model) {
    	String path = "schaltung?lenkertyp=" + order.getHandlebartype();
    	ArrayList result = getAllOptions(path);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForGearLevels");
    }
    
    @RequestMapping(value = "/processGearLevelForm", method= RequestMethod.POST)
    public String processGearLevelForm(@ModelAttribute(value="checklistForGearLevels") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        order.setGearLevels(checkedItems.get(0));
        System.out.println(order.toString());
        return "redirect:/produkt/griff";
    }
    
    @RequestMapping(value = "/produkt/griff", method= RequestMethod.GET)
    public ModelAndView showFormHandles(Model model) {
    	String path = "griff?material=" + order.getMaterial();
    	ArrayList result = getAllOptions(path);
        
        model.addAttribute("allItems", result);
        Checklist checklist = new Checklist();
        List<String> checkedItems = new ArrayList<String>();
        checkedItems.add((String) result.get(0));
        checklist.setCheckedItems(checkedItems);
        model.addAttribute("checklist", checklist);
        return new ModelAndView("checklistForHandles");
    }
    
    @RequestMapping(value = "/processHandleForm", method= RequestMethod.POST)
    public String processHandleForm(@ModelAttribute(value="checklistForHandle") Checklist checklist, Model model) {
        // Get value of checked item.
        List<String> checkedItems = checklist.getCheckedItems();
        order.setHandle(checkedItems.get(0));
        System.out.println(order.toString());

        return "redirect:/order";
    }
    
    @RequestMapping(value = "/order", method=RequestMethod.GET)
    public ModelAndView postOrder(Model model)  {  
    	String handlebartype = order.getHandlebartype();
    	String material = order.getMaterial();
    	String gearLevels = order.getGearLevels();
    	String handle = order.getHandle();
    	
        model.addAttribute("handlebartype", handlebartype);
        model.addAttribute("material", material);
        model.addAttribute("gearLevel", gearLevels);
        model.addAttribute("handle", handle);
        
        String sendOrderUrl = "https://www.maripavi.at/bestellung";
        
        if (handlebartype!=null){
        	sendOrderUrl += "?lenkertyp=" + handlebartype;
        }
        if (material!=null){
        	sendOrderUrl += "&material=" + material;
        }
        if (gearLevels!=null){
        	sendOrderUrl += "&schaltung=" + gearLevels;
        }
        if (handle!=null){
        	sendOrderUrl += "&griff=" + handle;
        }
        
        System.out.println(sendOrderUrl);
        
        try{
		    ResponseEntity<String> response = restTemplate.postForEntity(sendOrderUrl, "", String.class);
		    model.addAttribute("responseBody", response.getBody());
        }catch(HttpClientErrorException e){
        	Pattern pattern = Pattern.compile("detail\":\"(.*?)\"");
        	Matcher matcher = pattern.matcher(e.getResponseBodyAsString());
            if(matcher.find()) {
                model.addAttribute("error", "Bestellung fehlgeschlagen, " + matcher.group(1));
            }else {
            	model.addAttribute("error", "Bestellung fehlgeschlagen");
            }
        }
        
        return new ModelAndView("order");
    }
}
