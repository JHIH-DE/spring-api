package com.example.api.exception;

public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;

	private int statusCode = -1;

	public BaseException() {
		super();
	}

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
