package com.example.api.util;

public class CustomErrorType {
	private String message;
	private int code;

	public CustomErrorType(int code,String message){
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}
