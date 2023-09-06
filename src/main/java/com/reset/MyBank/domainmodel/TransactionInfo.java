package com.reset.MyBank.domainmodel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInfo {
	
	private Long accountNumber;
	private LocalDate transactionDateTime;
	private String transactionType;
	private Double transactionAmount;
}
