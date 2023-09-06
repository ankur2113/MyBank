package com.reset.MyBank.persistencemodel;

import java.time.LocalDate;
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
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRANSACTION_ID")
	private UUID id;
	
	//field accountNumber should be present in Account and CustomerAccountTransaction
	private Long accountNumber;
	
	private LocalDate transactionDateTime;
	private String transactionType;
	private Double transactionAmount;
}
