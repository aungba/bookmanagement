package com.bookmanagement.entity;

import javax.validation.constraints.NotBlank;

public class Category {

	private int category_id;

	@NotBlank
	private String category_txt;

	public Category() {
		super();
	}

	public Category(int category_id, @NotBlank String category_txt) {
		super();
		this.category_id = category_id;
		this.category_txt = category_txt;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_txt() {
		return category_txt;
	}

	public void setCategory_txt(String category_txt) {
		this.category_txt = category_txt;
	}

}
