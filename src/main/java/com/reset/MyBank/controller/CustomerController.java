package com.reset.MyBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reset.MyBank.domainmodel.CustomerInfo;
import com.reset.MyBank.service.MyBankServiceImplementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("customers")
@Api(tags = {"Endpoints for Customer"})
public class CustomerController {

	@Autowired
	private MyBankServiceImplementation myBankServiceimpl;
	
	@GetMapping("/allcustomers")
	@ApiOperation(value = "Get all customers", notes = "Get all customer details in a list")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public List<CustomerInfo> getAllCustomers(){
		return myBankServiceimpl.findAll();
	}
	
	
	@PostMapping("/addnewcustomer")
	@ApiOperation(value = "Add new customer", notes = "Add a new Customer")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> addNewCustomer(@RequestBody CustomerInfo customerInfo){
		return myBankServiceimpl.addNewCustomer(customerInfo);
	}
	
	
	@GetMapping("/getcustomerbyid/{customerNumber}")
	@ApiOperation(value = "find by customerNumber", notes = "Find a customer by it's customerNumber")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public CustomerInfo getCustomerById(@PathVariable Long customerNumber){
		return myBankServiceimpl.getCustomerByCustomerNumber(customerNumber);
	}
	
	
	@PutMapping("/updatecustomerbyid/{customerNumber}")
	@ApiOperation(value = "update by customerNumber", notes = "Update a customer information by it's customerNumber")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<String> updateCustomerById(@RequestBody CustomerInfo customerInfo, @PathVariable Long customerNumber){
		return myBankServiceimpl.updateCustomerByCustomerNumber(customerInfo, customerNumber);
	}
	
	
	
	@DeleteMapping("/deletecustomerbyid/{customerNumber}")
	@ApiOperation(value = "delete by customerNumber", notes = "Delete a customer by it's customerNumber")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> deleteCustomerById(@PathVariable Long customerNumber){
		return myBankServiceimpl.deleteCustomerByCustomerNumber(customerNumber);
	}
	
}
