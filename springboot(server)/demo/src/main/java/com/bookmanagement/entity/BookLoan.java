package com.bookmanagement.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookLoan {

	private int bookloan_id;
	private UserInfo user;
	private Book book;
	private Date rent_date;
	private Date due_date;
	private boolean is_loan;

}
