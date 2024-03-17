package com.userRegistartion.configration;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;


@Configuration
public class JwtConfig {

	@Bean
	public Key secretKey() {
		String mySecretKeyString = "QWFkaUlzR29pbmdUb0FsaXZlVGlsbHdvcmxkZ2V0c0RpdmlkZWRJbnRvcGljZXM";
		return Keys.hmacShaKeyFor(mySecretKeyString.getBytes());
	}

	//@Value("mySecretKeyString")
	//private String secretKey="QWFkaUlzR29pbmdUb0FsaXZlVGlsbHdvcmxkZ2V0c0RpdmlkZWRJbnRvcGljZXM";

	public Claims decodeToken(String token) {
		 try {
		        return Jwts.parser().setSigningKey(secretKey()).parseClaimsJws(token).getBody();
		    } catch (MalformedJwtException e) {
		        System.out.println("Malformed JWT token: " + e.getMessage());
		        // Log or handle the exception appropriately
		        throw e; // Rethrow the exception or handle it based on your requirements
		    } catch (Exception e) {
		        System.out.println("Error parsing JWT token: " + e.getMessage());
		        // Log or handle the exception appropriately
		        return null; // Return null or handle the error based on your requirements
		    }
	}
}
