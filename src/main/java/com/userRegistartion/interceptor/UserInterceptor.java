package com.userRegistartion.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.userRegistartion.configration.JwtConfig;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtConfig jwtConfig;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("Authorization");
		String tokString = request.getHeader("Authorization");
		if (token != null) {
			token = token.substring(7);
			System.out.println(token);
			System.out.println(tokString);
			try {
				String gmail = extractGmailFromToken(tokString);
				request.setAttribute("gmail", gmail);
				return true;
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invaild token");
				return false;
			}
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token not provided");
		return false;
	}

	public String extractGmailFromToken(String token) {
		Claims claims = jwtConfig.decodeToken(token);
		System.out.println(claims + " hey hhaaa");
		return claims.get("sub", String.class);
	}
}
