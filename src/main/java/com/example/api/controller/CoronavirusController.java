package com.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/")
public class CoronavirusController {

	@GetMapping("/coronavirus")
	public String getCoronavirus() {
		return String.format("Hello coronavirus");
	}
}
