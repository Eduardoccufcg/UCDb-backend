package Application.services;


import org.springframework.stereotype.Service;


import Application.model.User;
import Application.repositoriesDAO.UserDAO;

@Service
public class UserService {

	private final UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User create(User user) {

		return userDAO.save(user);

	}

	public User findByLogin(String login) {
		
		return this.userDAO.findByLogin(login);

	}

}
