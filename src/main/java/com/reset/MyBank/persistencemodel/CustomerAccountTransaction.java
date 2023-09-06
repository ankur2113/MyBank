package com.reset.MyBank.persistencemodel;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerAccountTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ACCOUNT_TRANSACTION_ID")
	private UUID id;
	
	//field accountNumber should be present in Transaction and Account
	private Long accountNumber;
	
	//field customerNumber should be present in Customer
	private Long customerNumber;
}
