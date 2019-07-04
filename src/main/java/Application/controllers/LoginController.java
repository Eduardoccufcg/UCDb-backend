package Application.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Application.exception.IncorrectPasswordException;
import Application.exception.UserNotFoundException;
import Application.model.LoginResponse;
import Application.model.User;
import Application.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Controlador de Login")
@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    private final String TOKEN_KEY = "psoft";

    @Autowired
    private UserService userService;
    @ApiOperation(value = "Faz o login de um usúario")
    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody User user) {

        // Recupera o usuario
        User authUser = userService.findByLogin(user.getEmail());

        // verificacoes
        if (authUser == null) {
            throw new UserNotFoundException("Usuario nao encontrado!");
        }

        if (!authUser.password().equals(user.password())) {
            throw new IncorrectPasswordException("Senha invalida!");
        }

        Claims claims = Jwts.claims().setSubject(authUser.getEmail());
        claims.put("firstName", authUser.getFirstName());
        claims.put("lastName", authUser.getLastName());

        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)).compact();
       
        return new LoginResponse(token);
    }
}
