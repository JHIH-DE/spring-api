package com.example.api.dao.repository;

import com.example.api.dao.entity.BaseUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BaseUserRepository extends CrudRepository<BaseUser, Integer>{
	@Override
	BaseUser save(BaseUser entity);

	@Override
	Optional<BaseUser> findById(Integer id);

	@Override
	boolean existsById(Integer id);

	@Override
	List<BaseUser> findAll();

	@Override
	void deleteById(Integer id);

	@Override
	void delete(BaseUser entity);

	Optional<BaseUser> findByName(String name);

}
