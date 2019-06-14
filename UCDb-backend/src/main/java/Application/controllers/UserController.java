package Application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Application.model.User;
import Application.services.EmailService;
import Application.services.UserService;

@RestController
@RequestMapping({ "/v1/users" })
public class UserController {

	private EmailService emailService;
	@Autowired
	private UserService userService;

	public UserController(UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;

	}

	@GetMapping(value = "/{login}")
	public ResponseEntity<User> getUser(@PathVariable String email) {
		User user = this.userService.findByLogin(email);
		if (user == null) {
			// not found
		}
		return new ResponseEntity<User>(this.userService.findByLogin(email), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	@ResponseBody
	public ResponseEntity<User> create(@RequestBody User user) {
		// Falta excecao de usuario ja existe.
		this.emailService.send("<" + user.getEmail() + ">");
		return new ResponseEntity<User>(this.userService.create(user), HttpStatus.CREATED);
	}

}
