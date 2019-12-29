package com.project.rest_bestellung;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class CustomerController {


    @RequestMapping("/customer/create")
    public String create(@RequestParam @Size(min=2, max=64) String firstName, 
    		@RequestParam @Size(min=2, max=64) String lastName) {
        
        return null;
    }
}
