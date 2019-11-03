package com.bookmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserInfoDto {
	
	private int id;
	
	private String username;
	
	private String role;
	
	private String address;
	
	private boolean gender;
	
	private String email;

}
