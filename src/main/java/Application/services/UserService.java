package Application.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import Application.model.User;
import Application.repositoriesDAO.UserDAO;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public UserService() {
		
	}

	public User create(User user) {

		return userDAO.save(user);

	}

	public User findByLogin(String login) {
		
		return this.userDAO.findByLogin(login);

	}

}
