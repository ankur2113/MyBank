package com.reset.MyBank.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reset.MyBank.domainmodel.AccountInfo;
import com.reset.MyBank.domainmodel.CustomerInfo;
import com.reset.MyBank.domainmodel.TransactionInfo;
import com.reset.MyBank.domainmodel.TransferInfo;

public interface MyBankService {
	
	public ResponseEntity<Object> findByAccountNumber(Long accountNumber);
	public ResponseEntity<Object> addNewAccount(AccountInfo accountInfo, Long customerNumber);
	public ResponseEntity<Object> transactionAndTransferDetails(TransferInfo transerInfo, Long customerNumber);
	public List<TransactionInfo> getTransactionsByAccountNumber(Long accountNumber);
	
	public List<CustomerInfo> findAll();
	public ResponseEntity<Object> addNewCustomer(CustomerInfo customerInfo);
	public CustomerInfo getCustomerByCustomerNumber(Long customerNumber);
	public ResponseEntity<String> updateCustomerByCustomerNumber(CustomerInfo customerInfo, Long customerNumber);
	public ResponseEntity<Object> deleteCustomerByCustomerNumber(Long customerNumber);

}
