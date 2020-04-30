package com.example.api.controller;

import com.example.api.dto.BaseUserDTO;
import com.example.api.dto.BaseUserMapper;
import com.example.api.exception.NoSuchResourceException;
import com.example.api.dao.entity.BaseUser;
import com.example.api.service.BaseUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/")
public class UserController {
	@Resource
	private BaseUserService baseUserService;

	@Resource
	private BaseUserMapper baseUserMapper;

	@GetMapping(value = "/users", produces = "application/json")
	public ResponseEntity<BaseUserDTO> getUsers() throws NoSuchResourceException {
		List<BaseUser> baseUsers = baseUserService.getBaseUsers();
		List<BaseUserDTO> BaseUsersDTO = baseUserMapper.toDTO(baseUsers);

		return new ResponseEntity(BaseUsersDTO, HttpStatus.OK);
	}

}
