package com.example.api.service;

import com.example.api.exception.NoSuchResourceException;
import com.example.api.dao.entity.BaseUser;

import java.util.List;

public interface BaseUserService {
	List<BaseUser> getBaseUsers() throws NoSuchResourceException;

	BaseUser getBaseUserById(Integer id) throws NoSuchResourceException;

	BaseUser addBaseUser(BaseUser entity);

	void deleteBaseUserById(Integer id) throws NoSuchResourceException;
}
