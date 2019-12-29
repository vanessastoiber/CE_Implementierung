package com.project.rest_bestellung;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Validated
public class CustomerController {

	private final AtomicLong nextId = new AtomicLong();
	
    @RequestMapping("/customer/create")
    public User create(@RequestParam @Size(min=2, max=64) String firstName, 
    		@RequestParam @Size(min=2, max=64) String lastName,
    		@RequestParam(required=false) String emailAddress,
    		@RequestParam(required=false) String username,
    		@RequestParam(required=false) String deliveryAddress,
    		@RequestParam(required=false) String invoiceAddress) {
    	long id = nextId.incrementAndGet();
    	User user = new User(id, emailAddress, firstName, lastName, username, deliveryAddress, invoiceAddress);
        
        return user;
    }
}
