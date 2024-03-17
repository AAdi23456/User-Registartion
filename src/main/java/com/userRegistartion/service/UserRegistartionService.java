package com.userRegistartion.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userRegistartion.configration.JwtConfig;
import com.userRegistartion.entity.Login;
import com.userRegistartion.entity.User;
import com.userRegistartion.reposatry.UserReposatry;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserRegistartionService {

	@Autowired
	private UserReposatry userReposatry;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Key secKey;
	@Autowired
	private JwtConfig jwtConfig;

	public Boolean registerUser(User user) {
		if (userReposatry.existsByEmail(user.getEmail())) {
			return false;
		}
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		userReposatry.save(user);
		return true;
	}

	public ResponseEntity<String> loginUser(Login login) {
		User user = userReposatry.findByEmail(login.getEmail());
		if (user == null || !passwordEncoder.matches(login.getPassword(), user.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email or password is wrong or both");
		}
		String token = Jwts.builder().setSubject(login.getEmail()).setIssuedAt(new Date())
				.signWith(secKey, SignatureAlgorithm.HS256).compact();
		return ResponseEntity.ok(token);
	}

	public ResponseEntity<Claims> tokenDetails(String token) {
		Claims claims = jwtConfig.decodeToken(token);
		return ResponseEntity.ok(claims);
	}

}
