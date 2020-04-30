package com.example.api.dao.entity;

public enum Role {
	admin("admin"), user("user"), guest("guest");

	private String role;

	Role(String role){
		this.role = role;
	}

	@Override
	public String toString() {
		return this.role;
	}

}