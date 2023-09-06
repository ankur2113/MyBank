package com.reset.MyBank.domainmodel;

import java.time.LocalDateTime;

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
public class AccountInfo {
	
	private Long accountNumber;
	private String accountStatus;
	private String accounttype;
	private Double accountBalance;
	private LocalDateTime accountCreated;
	private BankInfo bankinfo;
}
