package com.reset.MyBank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reset.MyBank.domainmodel.AccountInfo;
import com.reset.MyBank.domainmodel.CustomerInfo;
import com.reset.MyBank.domainmodel.TransactionInfo;
import com.reset.MyBank.domainmodel.TransferInfo;
import com.reset.MyBank.helper.MyBankServiceHelper;
import com.reset.MyBank.persistencemodel.Account;
import com.reset.MyBank.persistencemodel.Address;
import com.reset.MyBank.persistencemodel.Contact;
import com.reset.MyBank.persistencemodel.Customer;
import com.reset.MyBank.persistencemodel.CustomerAccountTransaction;
import com.reset.MyBank.persistencemodel.Transaction;
import com.reset.MyBank.repository.AccountRepository;
import com.reset.MyBank.repository.CustomerAccountTransactionRepository;
import com.reset.MyBank.repository.CustomerRepository;
import com.reset.MyBank.repository.TransactionRepository;

@Service
@Transactional
public class MyBankServiceImplementation implements MyBankService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private CustomerAccountTransactionRepository customerAccountTransactionRepository;
	
	@Autowired
	private MyBankServiceHelper myBankServiceHelper;
	
	public MyBankServiceImplementation (CustomerRepository repository) {
		this.customerRepository= repository;
	}
	
	
	public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {
		Optional<Account> accountEntityOpt =  accountRepository.findByAccountNumber(accountNumber);
		if(accountEntityOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(myBankServiceHelper.convertToAccountDomain(accountEntityOpt.get()));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with : "+accountNumber+" was not found.");
		}
	}

	public ResponseEntity<Object> addNewAccount(AccountInfo accountInfo, Long customerNumber) {
		Optional<Customer> customerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);
		if(customerEntityOpt.isPresent()) {
			accountRepository.save(myBankServiceHelper.convertToAccountInfoEntity(accountInfo));
			customerAccountTransactionRepository.save(CustomerAccountTransaction.builder()
					.accountNumber(accountInfo.getAccountNumber())
					.customerNumber(customerNumber).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("New Account created successfully.");
	}

	public ResponseEntity<Object> transactionAndTransferDetails(TransferInfo transerInfo, Long customerNumber) {
		List<Account> accountEntities = new ArrayList<>();
		Account fromAccountEntity = null;
		Account toAccountEntity = null;
		Optional<Customer> customerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);
		
		if(customerEntityOpt.isPresent()) {
			Optional<Account> fromAccountEntityOpt = accountRepository.findByAccountNumber(transerInfo.getFromAccountNumber());
			if(fromAccountEntityOpt.isPresent()) {
				fromAccountEntity = fromAccountEntityOpt.get();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number: "+transerInfo.getFromAccountNumber()+" was not found.");
			}
			
			Optional<Account> toAccountEntityOpt = accountRepository.findByAccountNumber(transerInfo.getToAccountNumber());
			if(toAccountEntityOpt.isPresent()) {
				toAccountEntity = toAccountEntityOpt.get();
			}else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number: "+transerInfo.getToAccountNumber()+" was not found.");
			}
			
			if(fromAccountEntity.getAccountBalance()<transerInfo.getTransferAmount()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
			}else {
				synchronized (this) {
					fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance()-transerInfo.getTransferAmount());
					fromAccountEntity.setUpdatedDateTime(LocalDate.now());
					accountEntities.add(fromAccountEntity);
					
					toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance()+transerInfo.getTransferAmount());
					toAccountEntity.setUpdatedDateTime(LocalDate.now());
					accountEntities.add(toAccountEntity);
					
					accountRepository.saveAll(accountEntities);
					
					Transaction fromTransaction = myBankServiceHelper.createTransaction(transerInfo, fromAccountEntity.getAccountNumber(), "Debit");
					transactionRepository.save(fromTransaction);
					
					Transaction toTransaction = myBankServiceHelper.createTransaction(transerInfo, toAccountEntity.getAccountNumber(), "Credit");
					transactionRepository.save(toTransaction);
				}
				return ResponseEntity.status(HttpStatus.OK).body("Success: Amount transferred for Customer Number " + customerNumber);
			}
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Number " + customerNumber + " not found.");
		}
	}

	public List<TransactionInfo> getTransactionsByAccountNumber(Long accountNumber) {
		List<TransactionInfo> transactionInfo = new ArrayList<>();
		Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountEntityOpt.isPresent()) {
			Optional<List<Transaction>> transactionEntitiesOpt = transactionRepository.findByAccountNumber(accountNumber);
			if(transactionEntitiesOpt.isPresent()) {
				transactionEntitiesOpt.get().forEach(transaction -> {
				transactionInfo.add(myBankServiceHelper.convertToTransactionDomain(transaction));	
				});
			}
		}
		return transactionInfo;
	}

	
	
	public List<CustomerInfo> findAll() {
		List<CustomerInfo> listOfCustomerInfo = new ArrayList<CustomerInfo>();
		Iterable<Customer> listOfCustomer = customerRepository.findAll();
		listOfCustomer.forEach(customer -> {
			listOfCustomerInfo.add(myBankServiceHelper.convertToCustomerDomain(customer));
	        });
		return listOfCustomerInfo;
	}

	public ResponseEntity<Object> addNewCustomer(CustomerInfo customerInfo) {
		Customer customer = myBankServiceHelper.convertToCustomerEntity(customerInfo);
		customer.setCreatedDateTime(LocalDate.now());
		customerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body("New Customer created successfully.");
	}

	public CustomerInfo getCustomerByCustomerNumber(Long customerNumber) {
		Optional<Customer> customer = customerRepository.findByCustomerNumber(customerNumber);
		if(customer.isPresent())
			return myBankServiceHelper.convertToCustomerDomain(customer.get());
		return null;
	}

	public ResponseEntity<String> updateCustomerByCustomerNumber(CustomerInfo customerInfo, Long customerNumber) {
		
		Optional<Customer> managedCustomerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);
		
		Customer unmanagedCustomerEntity = myBankServiceHelper.convertToCustomerEntity(customerInfo);
		
		if(managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();
		
		if(Optional.ofNullable(unmanagedCustomerEntity.getCustomerContact()).isPresent()) {
			Contact managedContact = managedCustomerEntity.getCustomerContact();
			if(managedContact!=null) {
				managedContact.setEmailId(unmanagedCustomerEntity.getCustomerContact().getEmailId());
				managedContact.setHomePhone(unmanagedCustomerEntity.getCustomerContact().getHomePhone());
				managedContact.setWorkPhone(unmanagedCustomerEntity.getCustomerContact().getWorkPhone());
			}
			else {
				managedCustomerEntity.setCustomerContact(unmanagedCustomerEntity.getCustomerContact());
			}
		}
		
		if(Optional.ofNullable(unmanagedCustomerEntity.getCustomerAddress()).isPresent()) {
			Address managedAddress = managedCustomerEntity.getCustomerAddress();
			if(managedAddress!=null) {
				managedAddress.setAddress1(unmanagedCustomerEntity.getCustomerAddress().getAddress1());
				managedAddress.setAddress2(unmanagedCustomerEntity.getCustomerAddress().getAddress2());
				managedAddress.setCity(unmanagedCustomerEntity.getCustomerAddress().getCity());
				managedAddress.setCountry(unmanagedCustomerEntity.getCustomerAddress().getCountry());
				managedAddress.setState(unmanagedCustomerEntity.getCustomerAddress().getState());
				managedAddress.setZipcode(unmanagedCustomerEntity.getCustomerAddress().getZipcode());
			}
			else {
				managedCustomerEntity.setCustomerAddress(unmanagedCustomerEntity.getCustomerAddress());
			}
		}
		managedCustomerEntity.setUpdatedDateTime(LocalDate.now());
		managedCustomerEntity.setCustomerStatus(unmanagedCustomerEntity.getCustomerStatus());
		managedCustomerEntity.setFirstName(unmanagedCustomerEntity.getFirstName());
		managedCustomerEntity.setMiddleName(unmanagedCustomerEntity.getMiddleName());
		managedCustomerEntity.setLastName(unmanagedCustomerEntity.getLastName());
		
		customerRepository.save(managedCustomerEntity);
		return ResponseEntity.status(HttpStatus.OK).body("Customer Updated Successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer :"+customerNumber+" was not found.");
		}
	}

	public ResponseEntity<Object> deleteCustomerByCustomerNumber(Long customerNumber) {
		Optional<Customer> managedCustomerEntityOpt = customerRepository.findByCustomerNumber(customerNumber);
		if(managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();
			customerRepository.delete(managedCustomerEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Customer was deleted successfully.");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with :"+customerNumber+" was not found.");
		}
	}

	
}
