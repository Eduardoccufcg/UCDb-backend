package Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import Application.exception.EmptyPasswordException;

import Application.exception.UserAlreadyExistsException;

import Application.model.User;

import Application.services.UserService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping({ "/v1/users" })
public class UserController {
	
//	@Autowired
//	private EmailService emailService;

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Cadastra um novo usuário")
	@PostMapping(value = "/")
	public ResponseEntity<User> create(@RequestBody User user) {

		if (user.password() == null | user.password().isEmpty()) {
			throw new EmptyPasswordException("Senha inválida");
		}

		if (this.userService.findByLogin(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("Usuário já cadastrado");
		}
//		try {
//			// Falta passar pra html
//			this.emailService.send("<" + user.getEmail() + ">");
//		} catch (RuntimeException e) {
//			throw new InvalidEmailException("Email Inválido");
//		}

		return new ResponseEntity<User>(this.userService.create(user), HttpStatus.CREATED);
	}
}
