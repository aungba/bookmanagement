package com.bookmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserAddress {
	private int address_id;
	private String address;
	private String city;
	private String country;
	private String postalCode;

}
