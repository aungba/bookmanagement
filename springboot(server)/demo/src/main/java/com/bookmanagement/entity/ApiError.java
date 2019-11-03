package com.bookmanagement.entity;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus httpStatus;
	private String message;
	private List<String> errors;

	public ApiError() {
		super();
	}

	public ApiError(HttpStatus httpStatus, String message, List<String> errors) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.errors = errors;
	}

	public ApiError(final HttpStatus httpStatus, final String message, final String error) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
