package com.project.rest_bestellung;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
    @NotNull
    @Size(min=2, max=30)
    private String firstName;
    @NotNull
    @Size(min=2, max=30)
    private String lastName;
    
    private Long id;
    private String emailAddress;
    private String username;
    private String deliveryAddress;
    private String invoiceAddress;
    
    public User() {
    	
    }
    
    public User(Long id, String email, String vorname, String nachname, String username, String lieferadresse, String rechnungsadresse) {
        this.id = id;
        this.emailAddress = email;
        this.firstName = vorname;
        this.lastName = nachname;
        this.username = username;
        this.deliveryAddress = lieferadresse;
        this.invoiceAddress = rechnungsadresse;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	private List<String> checkedItems;

    public List<String> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(List<String> checkedItems) {
        this.checkedItems = checkedItems;
    }


    
}