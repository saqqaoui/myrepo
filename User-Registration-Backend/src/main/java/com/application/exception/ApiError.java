package com.application.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private final String message;

	private final List<String> errors;

	private final HttpStatus status;

	public ApiError() {
		super();
		this.message = "";
		this.status = HttpStatus.NOT_IMPLEMENTED;
		this.errors = new ArrayList<String>();
	}

	public ApiError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public String getMessage() {
		return message;
	}
	public List<String> getErrors() {
		return errors;
	}
	public HttpStatus getStatus() {
		return status;
	}
}