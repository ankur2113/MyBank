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
public class BankInfo {
	
	private String branchName;
	private Integer branchCode;
	private Integer routingCode;
	private AddressInfo branchaddress;
}
