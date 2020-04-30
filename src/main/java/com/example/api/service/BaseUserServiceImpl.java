package com.example.api.service;

import com.example.api.dao.repository.BaseUserRepository;
import com.example.api.exception.NoSuchResourceException;
import com.example.api.dao.entity.BaseUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("BaseUserService")
public class BaseUserServiceImpl implements BaseUserService{
	@Resource
	private BaseUserRepository baseUserRepository;

	@Override
	public List<BaseUser> getBaseUsers() throws NoSuchResourceException {
		List<BaseUser> users = baseUserRepository.findAll();
		if (users != null) {
			return users;
		} else {
			throw new NoSuchResourceException();
		}
	}
}
