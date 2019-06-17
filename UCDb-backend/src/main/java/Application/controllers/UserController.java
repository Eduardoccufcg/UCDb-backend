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

import Application.exception.UserAlreadyExistsException;
import Application.exception.UserNotFoundException;
import Application.model.User;
import Application.services.EmailService;
import Application.services.UserService;

@RestController
@RequestMapping({"/v1/users"})
public class UserController {
    @Autowired
    private EmailService emailService;

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{login}")
    public ResponseEntity<User> getUser(@PathVariable String email) {
        User user = this.userService.findByLogin(email);
        if (user == null) {
            throw new UserNotFoundException("Usuário Inexistente");
        }
        return new ResponseEntity<User>(this.userService.findByLogin(email), HttpStatus.OK);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) {
        if (this.userService.findByLogin(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
        this.emailService.send("<" + user.getEmail() + ">");
        return new ResponseEntity<User>(this.userService.create(user), HttpStatus.CREATED);
    }
}
