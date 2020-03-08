package Application.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.exception.EmptyPasswordException;
import Application.exception.UserAlreadyExistsException;
import Application.model.User;
import Application.repositoriesDAO.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	

	public User create(User user) {
		
		if (user.password() == null | user.password().isEmpty()) {
			throw new EmptyPasswordException("Senha inválida");
		}

		if (this.search(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("Usuário já cadastrado");
		}

		return userDAO.save(user);

	}
	
	public User search(String email) {
		User user = null;
		Optional<User> userAux = userDAO.findById(email);
		if(userAux.isPresent()) {
			user = userAux.get();
			
		}
		return user;
	}

}
