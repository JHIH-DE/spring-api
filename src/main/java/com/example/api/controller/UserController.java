package com.example.api.controller;

import com.example.api.dto.BaseUserDTO;
import com.example.api.dto.BaseUserMapper;
import com.example.api.exception.NoSuchResourceException;
import com.example.api.dao.entity.BaseUser;
import com.example.api.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.api.util.CustomErrorType;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/")
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private BaseUserService baseUserService;

	@Resource
	private BaseUserMapper baseUserMapper;

	@GetMapping(value = "users", produces = "application/json")
	public ResponseEntity<BaseUserDTO> getUsers() throws NoSuchResourceException {
		logger.info("Fetching All User");

		List<BaseUser> baseUsers = baseUserService.getBaseUsers();
		List<BaseUserDTO> baseUsersDTO = baseUserMapper.toDTO(baseUsers);

		return new ResponseEntity(baseUsersDTO, HttpStatus.OK);
	}

	@GetMapping(value = "users/{id}", produces = "application/json")
	public ResponseEntity<Object> getUserById(@PathVariable Integer id) throws NoSuchResourceException {
		logger.info("Fetching User with id {}", id);

		BaseUser baseUser = baseUserService.getBaseUserById(id);
		BaseUserDTO baseUserDTO = baseUserMapper.toDTO(baseUser);
		return new ResponseEntity(baseUserDTO, HttpStatus.OK);
	}

	@PostMapping(value = "users", produces = "application/json")
	public ResponseEntity<Object> addUsers(@RequestBody BaseUserDTO baseUserDTO) throws NoSuchResourceException {
		logger.info("Creating User : {}", baseUserDTO);

		BaseUser baseUser = baseUserMapper.toEntity(baseUserDTO);
		baseUser = baseUserService.addBaseUser(baseUser);
		baseUserDTO = baseUserMapper.toDTO(baseUser);
		return new ResponseEntity(baseUserDTO, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "users/{id}", produces = "application/json")
	public ResponseEntity<Object> deleteUsers(@PathVariable Integer id) throws NoSuchResourceException {
		logger.info("Fetching & Deleting User with id {}", id);

		BaseUser user = baseUserService.getBaseUserById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		baseUserService.deleteBaseUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
