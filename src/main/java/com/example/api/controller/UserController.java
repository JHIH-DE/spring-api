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
	public ResponseEntity<Object> getUsers() throws NoSuchResourceException {
		logger.info("Fetching All User");

		List<BaseUser> baseUsers = baseUserService.getBaseUsers();
		List<BaseUserDTO> baseUsersDTO = baseUserMapper.toDTO(baseUsers);
		if (baseUsersDTO.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity(baseUsersDTO, HttpStatus.OK);
	}

	@GetMapping(value = "users/{id}", produces = "application/json")
	public ResponseEntity<Object> getUserById(@PathVariable Integer id) {
		logger.info("Fetching User with id {}", id);
		try {
			BaseUser baseUser = baseUserService.getBaseUserById(id);
			BaseUserDTO baseUserDTO = baseUserMapper.toDTO(baseUser);
			return new ResponseEntity(baseUserDTO, HttpStatus.OK);
		} catch (NoSuchResourceException e) {
			logger.error("User with id {} not found.", id);
			int code = HttpStatus.NOT_FOUND.value();
			String errorMsg = "User with id " + id + " not found";
			return new ResponseEntity(new CustomErrorType(code, errorMsg), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "users", produces = "application/json")
	public ResponseEntity<Object> addUsers(@RequestBody BaseUserDTO baseUserDTO) throws NoSuchResourceException {
		logger.info("Creating User : {}", baseUserDTO);
		BaseUser baseUser = baseUserMapper.toEntity(baseUserDTO);
		if (baseUserService.isUserExist(baseUser)) {
			logger.error("Unable to create. A User with name {} already exist", baseUserDTO.getName());
			int code = HttpStatus.CONFLICT.value();
			String errorMsg = "Unable to create. A User with name " + baseUserDTO.getName() + " already exist.";
			return new ResponseEntity(new CustomErrorType(code, errorMsg), HttpStatus.CONFLICT);
		}

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
			int code = HttpStatus.NOT_FOUND.value();
			String errorMsg = "Unable to delete. User with id " + id + " not found.";
			return new ResponseEntity(new CustomErrorType(code, errorMsg), HttpStatus.NOT_FOUND);
		}
		baseUserService.deleteBaseUserById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
