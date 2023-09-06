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
public class CustomerInfo {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private Long customerNumber;
	private String customerStatus;
	private AddressInfo customerAddressInfo;
	private ContactInfo customerContactInfo;
}
