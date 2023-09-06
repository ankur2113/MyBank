package com.reset.MyBank.persistencemodel;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID")
	private UUID id;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	//field customerNumber should be present in CustomerAccountTransaction
	private Long customerNumber;
	
	private String customerStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Address customerAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Contact customerContact;
	
	private LocalDate createdDateTime;
	private LocalDate updatedDateTime;
}
