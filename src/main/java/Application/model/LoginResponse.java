package Application.model;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Jwts;

public class LoginResponse {
	
	private String token;

	public LoginResponse(String token) {
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
