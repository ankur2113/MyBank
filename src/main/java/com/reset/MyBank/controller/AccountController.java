package com.reset.MyBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reset.MyBank.domainmodel.AccountInfo;
import com.reset.MyBank.domainmodel.TransactionInfo;
import com.reset.MyBank.domainmodel.TransferInfo;
import com.reset.MyBank.service.MyBankServiceImplementation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("accounts")
@Api(tags = {"Endpoints for Accounts and Transactions"})
public class AccountController {
	
	@Autowired
	private MyBankServiceImplementation myBankServiceimpl;
	
	@GetMapping("/{accountNumber}")
	@ApiOperation(value = "Get account details", notes = "Find account details by account number")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> getByAccountNumber(@PathVariable Long accountNumber){
		return myBankServiceimpl.findByAccountNumber(accountNumber);
	}
	
	
	@PostMapping("/addnewaccount/{customerNumber}")
	@ApiOperation(value = "Add new account", notes = "Creates new Account for the existing customer")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> addNewAccount(@RequestBody AccountInfo accountInfo, @PathVariable Long customerNumber){
		return myBankServiceimpl.addNewAccount(accountInfo, customerNumber);
	}

	
	@PutMapping("/transfer/{customerNumber}")
	@ApiOperation(value = "Transfer funds between accounts", notes = "Transfer funds between accounts of a customer")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public ResponseEntity<Object> transactionAndTransferDetails(@RequestBody TransferInfo transerInfo, @PathVariable Long customerNumber){
		return myBankServiceimpl.transactionAndTransferDetails(transerInfo, customerNumber);
	}
	
	
	@GetMapping("/transactions/{accountNumber}")
	@ApiOperation(value = "Get Transactions", notes = "Find all Transactions by account number")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	public List<TransactionInfo> getTransactionsByAccountNumber(@PathVariable Long accountNumber){
		return myBankServiceimpl.getTransactionsByAccountNumber(accountNumber);
	}
}
