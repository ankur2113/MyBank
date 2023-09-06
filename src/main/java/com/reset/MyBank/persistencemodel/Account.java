package com.reset.MyBank.persistencemodel;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private UUID id;
	
	//field accountNumber should be present in Transaction and CustomerAccountTransaction
	private Long accountNumber;
	
	private String accountStatus;
	private String accountType;
	private Double accountBalance;
	private LocalDate createdDateTime;
	private LocalDate updatedDateTime;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Bank bank;
}
