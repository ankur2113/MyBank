package com.reset.MyBank.helper;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import com.reset.MyBank.domainmodel.AccountInfo;
import com.reset.MyBank.domainmodel.AddressInfo;
import com.reset.MyBank.domainmodel.BankInfo;
import com.reset.MyBank.domainmodel.ContactInfo;
import com.reset.MyBank.domainmodel.CustomerInfo;
import com.reset.MyBank.domainmodel.TransactionInfo;
import com.reset.MyBank.domainmodel.TransferInfo;
import com.reset.MyBank.persistencemodel.Account;
import com.reset.MyBank.persistencemodel.Address;
import com.reset.MyBank.persistencemodel.Bank;
import com.reset.MyBank.persistencemodel.Contact;
import com.reset.MyBank.persistencemodel.Customer;
import com.reset.MyBank.persistencemodel.Transaction;

@Component
public class MyBankServiceHelper {

	public CustomerInfo convertToCustomerDomain(Customer customer) {
		return CustomerInfo.builder()
				.firstName(customer.getFirstName())
				.middleName(customer.getMiddleName())
				.lastName(customer.getLastName())
				.customerNumber(customer.getCustomerNumber())
				.customerStatus(customer.getCustomerStatus())
				.customerAddressInfo(convertToAddressDomain(customer.getCustomerAddress()))
				.customerContactInfo(convertToContactDomain(customer.getCustomerContact())).build();
	}

	public ContactInfo convertToContactDomain(Contact customerContact) {
		return ContactInfo.builder()
				.emailId(customerContact.getEmailId())
				.homePhone(customerContact.getHomePhone())
				.workPhone(customerContact.getWorkPhone()).build();
	}

	public AddressInfo convertToAddressDomain(Address customerAddress) {
		return AddressInfo.builder()
				.address1(customerAddress.getAddress1())
				.address2(customerAddress.getAddress2())
				.city(customerAddress.getCity())
				.state(customerAddress.getState())
				.zipcode(customerAddress.getZipcode())
				.country(customerAddress.getCountry()).build();
	}
	
	public AccountInfo convertToAccountDomain(Account account) {
		return AccountInfo.builder()
				.accountNumber(account.getAccountNumber())
				.accounttype(account.getAccountType())
				.accountStatus(account.getAccountStatus())
				.accountBalance(account.getAccountBalance())
				.bankinfo(convertToBankDomain(account.getBank())).build();
	}

	private BankInfo convertToBankDomain(Bank bank) {
		return BankInfo.builder()
				.branchCode(bank.getBranchCode())
				.branchName(bank.getBranchName())
				.routingCode(bank.getRoutingNumber())
				.branchaddress(convertToAddressDomain(bank.getBranchAddress())).build();
				
	}
	
	public TransactionInfo convertToTransactionDomain(Transaction transaction) {
		return TransactionInfo.builder()
				.transactionAmount(transaction.getTransactionAmount())
				.transactionType(transaction.getTransactionType())
				.transactionDateTime(transaction.getTransactionDateTime())
				.accountNumber(transaction.getAccountNumber()).build();
	}
	
	public Customer convertToCustomerEntity(CustomerInfo customerInfo) {
		return Customer.builder()
				.firstName(customerInfo.getFirstName())
				.middleName(customerInfo.getMiddleName())
				.lastName(customerInfo.getLastName())
				.customerNumber(customerInfo.getCustomerNumber())
				.customerStatus(customerInfo.getCustomerStatus())
				.customerContact(convertToContactEntity(customerInfo.getCustomerContactInfo()))
				.customerAddress(convertToAddressEntity(customerInfo.getCustomerAddressInfo())).build();
	}

	public Address convertToAddressEntity(AddressInfo customerAddressInfo) {
		return Address.builder()
				.address1(customerAddressInfo.getAddress1())
				.address2(customerAddressInfo.getAddress2())
				.city(customerAddressInfo.getCity())
				.state(customerAddressInfo.getState())
				.zipcode(customerAddressInfo.getZipcode())
				.country(customerAddressInfo.getCountry()).build();
	}

	public Contact convertToContactEntity(ContactInfo customerContactInfo) {
		return Contact.builder()
				.emailId(customerContactInfo.getEmailId())
				.homePhone(customerContactInfo.getHomePhone())
				.workPhone(customerContactInfo.getWorkPhone()).build();
	}
	
	public Bank convertToBankInfoEntity(BankInfo bankInfo) {
		return Bank.builder()
				.branchName(bankInfo.getBranchName())
				.branchCode(bankInfo.getBranchCode())
				.branchAddress(convertToAddressEntity(bankInfo.getBranchaddress()))
				.routingNumber(bankInfo.getRoutingCode()).build();
	}
	
	public Account convertToAccountInfoEntity(AccountInfo accountInfo) {
		return Account.builder()
				.accountNumber(accountInfo.getAccountNumber())
				.accountStatus(accountInfo.getAccountStatus())
				.accountType(accountInfo.getAccounttype())
				.accountBalance(accountInfo.getAccountBalance())
				.bank(convertToBankInfoEntity(accountInfo.getBankinfo())).build();
	}
	public Transaction convertToTransactionInfoEntity(TransactionInfo transactionInfo) {
		return Transaction.builder()
				.accountNumber(transactionInfo.getAccountNumber())
				.transactionDateTime(transactionInfo.getTransactionDateTime())
				.transactionType(transactionInfo.getTransactionType())
				.transactionAmount(transactionInfo.getTransactionAmount()).build();
    }
	
	public Transaction createTransaction(TransferInfo transferInfo, Long accountNumber, String transactionType) {
		return Transaction.builder()
				.accountNumber(accountNumber)
				.transactionAmount(transferInfo.getTransferAmount())
				.transactionType(transactionType)
				.transactionDateTime(LocalDate.now()).build();
	}
}
