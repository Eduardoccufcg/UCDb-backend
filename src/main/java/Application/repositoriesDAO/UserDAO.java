package Application.repositoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Application.model.User;


@Repository
public interface UserDAO extends JpaRepository<User,String> {
	

}
