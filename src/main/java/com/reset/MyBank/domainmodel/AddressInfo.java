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
public class AddressInfo {
	
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
}
