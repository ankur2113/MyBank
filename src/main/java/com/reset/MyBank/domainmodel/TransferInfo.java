package com.reset.MyBank.domainmodel;

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
public class TransferInfo {
	
	private Long fromAccountNumber;
	private Long toAccountNumber;
	private Double transferAmount;
}
