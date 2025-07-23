package com.oracle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.exception.DuplicateCustomerException;
import com.oracle.exception.NoSuchCustomerException;
import com.oracle.model.Customer;
import com.oracle.service.CustomerService;

@RestController
@RequestMapping(path="customer-api")
public class CustomerRestController {

	@Autowired
	private CustomerService service;
	@PostMapping
	public void addCustomer(@RequestBody Customer cust)
	{
		service.addCustomer(cust);
	}
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<String> handleDuplicateCustomerException(DuplicateCustomerException ex){
		return ResponseEntity.status(500).body(ex.getMessage());
	}
	
	//http://localhost:8080/customer-api
	@GetMapping("/all")
	public List<Customer> getAllCustomers()
	{
		List<Customer> customers = service.findAllCustomers();
		return customers;
	}
	@GetMapping("/email")
	public Customer getCustomerByEmail(@PathVariable String email) {
		return service.findCustomerByEmail(email);
	}
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<String> handleNoSuchException(NoSuchCustomerException ex){
		return ResponseEntity.status(500).body(ex.getMessage());
	}
	
}
