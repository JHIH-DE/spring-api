package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class NoSuchResourceException extends BaseException {
	private static final long serialVersionUID = 1L;

	public NoSuchResourceException() {
		super("The resource is not found.");
		this.setStatusCode(HttpStatus.NOT_FOUND.value());
	}

	public NoSuchResourceException(String resourceName) {
		super("The resource is not found. " + resourceName);
		this.setStatusCode(HttpStatus.NOT_FOUND.value());
	}
}
