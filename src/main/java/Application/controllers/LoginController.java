package Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Application.model.LoginResponse;
import Application.model.User;
import Application.services.LoginService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@ApiOperation(value = "Faz o login de um usúario")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {

		return new ResponseEntity<LoginResponse>(this.loginService.authenticate(user), HttpStatus.OK);
	}
}
