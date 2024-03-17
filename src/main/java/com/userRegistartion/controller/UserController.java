package com.userRegistartion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userRegistartion.entity.Login;
import com.userRegistartion.entity.User;
import com.userRegistartion.service.UserRegistartionService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRegistartionService userRegistartionService;

	@PostMapping
	public Boolean saveUser(@RequestBody User user) {
		return userRegistartionService.registerUser(user);
	}

	@GetMapping("/cred")
	public String getEndpoint() {
		System.out.println("get endpoint");
		return "login successful";

	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Login login) {
		return userRegistartionService.loginUser(login);
	}

	@GetMapping("/protected")
	public String decoded(HttpServletRequest request) {
		String gmail = (String) request.getAttribute("gmail");
		return gmail +"  hey its working";
	}
}
