package com.example.api.service;

import com.example.api.dao.repository.BaseUserRepository;
import com.example.api.exception.NoSuchResourceException;
import com.example.api.dao.entity.BaseUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

	@Override
	public BaseUser getBaseUserById(Integer id) throws NoSuchResourceException {
		Optional<BaseUser> user = baseUserRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NoSuchResourceException();
		}
	}

	@Override
	public BaseUser addBaseUser(BaseUser entity){
		return baseUserRepository.save(entity);
	}

	@Override
	public void deleteBaseUserById(Integer id) throws NoSuchResourceException{
		baseUserRepository.deleteById(id);
	}

	@Override
	public Boolean isUserExist(BaseUser user){
		Optional<BaseUser> entity = baseUserRepository.findByName(user.getName());
		return  entity.isPresent();
	}
}
