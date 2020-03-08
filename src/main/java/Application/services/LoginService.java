package Application.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import Application.exception.LoginErrorException;
import Application.model.LoginResponse;
import Application.model.User;
import Application.repositoriesDAO.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {

	private final String TOKEN_KEY = "psoft";

	@Autowired
	private UserDAO userDAO;

	public LoginResponse authenticate(@RequestBody User user) {

		// Recupera o usuario
		Optional<User> authUserAux = userDAO.findById(user.getEmail());

		// verificacoes
		if (authUserAux.isPresent()) {

			User authUser = authUserAux.get();
			if (!user.password().equals(authUser.password())) {

				throw new LoginErrorException("Login ou senha inválidos");
			}

			Claims claims = Jwts.claims().setSubject(authUser.getEmail());
			claims.put("firstName", authUser.getFirstName());
			claims.put("lastName", authUser.getLastName());

			String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
					.setExpiration(new Date(System.currentTimeMillis() + 86_400_000)).compact();

			return new LoginResponse(token);
		}

		throw new LoginErrorException("Login ou senha inválidos");

	}

}
