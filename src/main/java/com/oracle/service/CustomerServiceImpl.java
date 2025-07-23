package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.CustomerDao;
import com.oracle.exception.DuplicateCustomerException;
import com.oracle.exception.NoSuchCustomerException;
import com.oracle.model.Customer;

@Service
public class CustomerServiceImpl implements  CustomerService{

	@Autowired
	private CustomerDao dao;
	@Override
	public void addCustomer(Customer cust) {
	    Customer existing = dao.readCustomerByEmail(cust.getEmail());
	    if (existing != null) {
	        throw new DuplicateCustomerException(cust.getEmail());
	    }
	    dao.createCustomer(cust);
	}

	@Override
	public Customer findCustomerByEmail(String email) {
		
		Customer customer = dao.readCustomerByEmail(email);
		if(customer == null) {
			throw new NoSuchCustomerException("no such with email - "+email);
		}
		
		
		return customer;
		
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return dao.readAllCustomers();
	}

	
}
