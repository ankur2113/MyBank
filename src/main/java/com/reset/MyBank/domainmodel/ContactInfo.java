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
public class ContactInfo {
	
	private String emailId;
	private String homePhone;
	private String workPhone;
}
