package com.userRegistartion;
import java.security.Key;
import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class UserRegistartionApplication {

	public static void main(String[] args) {
		 // Generate a secret key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // Convert the key to base64url format
        String base64UrlEncodedKey = Base64.getUrlEncoder().encodeToString(key.getEncoded());
        // Print the base64url-encoded key
        System.out.println(base64UrlEncodedKey);
		SpringApplication.run(UserRegistartionApplication.class, args);
		
	}

}
