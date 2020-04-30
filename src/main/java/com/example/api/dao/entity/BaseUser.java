package com.example.api.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="accounts")
public class BaseUser {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	@Size(max = 30)
	@Column(name="username",length = 30)
	private String name;

	@Size(max = 40)
	@Column(length = 40)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}