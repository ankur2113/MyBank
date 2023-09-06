package com.reset.MyBank.persistencemodel;

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
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BANK_ID")
	private UUID id;
	
	private String branchName;
	private Integer branchCode;
	private Integer routingNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address branchAddress;
}
