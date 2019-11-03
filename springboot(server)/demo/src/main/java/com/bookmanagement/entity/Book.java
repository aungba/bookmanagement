package com.bookmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Book")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	
	@NotBlank
	@Column(unique = true)
	private String title;
	
	@NotBlank
	@Size(max = 50)
	private String author;
	
	@NotBlank
	private String publisher;
	
	@NotBlank
	private String summary;
	
	@NotNull
	private Date release_date;
	
	@NotNull
	private Boolean status;
	
	@NotBlank
	private String isAvailable;
	

	private Category category;
	

	private Date rent_date;

}
