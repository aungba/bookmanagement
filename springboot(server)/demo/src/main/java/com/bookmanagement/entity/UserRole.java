package com.bookmanagement.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserRole {
	
	private int role_id;
	private String role_name;
	private String role_description;
	
}
