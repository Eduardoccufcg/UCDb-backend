package Application.model;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
@Component
public class TokenParseEmail {

	public String tokenParseEmail(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;

		String header = req.getHeader("Authorization");

		String token = header.substring(7);

		String email = Jwts.parser().setSigningKey("psoft").parseClaimsJws(token).getBody().getSubject();

		return email;
	}

}
