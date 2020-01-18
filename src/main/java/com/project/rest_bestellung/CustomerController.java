package com.project.rest_bestellung;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CustomerController {	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
    public String checkCustomerInfo(@Valid User customerForm, BindingResult bindingResult, Model model, RedirectAttributes redirAttr) {

        if (bindingResult.hasErrors()) {
        	model.addAttribute("customerForm", customerForm);
            return "/error";
        }
        
        redirAttr.addFlashAttribute("user", "Kundenkonto " + customerForm.getFirstName() + " " + customerForm.getLastName() + " erfolgreich erstellt.");
        return "redirect:/produkt/lenkertyp";
    }
    
    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public ModelAndView showCustomerForm(Model model) {
        model.addAttribute("customerForm", new User());
        return new ModelAndView("createCustomer");
    }

}
