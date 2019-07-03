package Application.repositoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Application.model.User;


@Repository
public interface UserDAO extends JpaRepository<User,String> {
	
	@Query(value="SELECT u FROM User u WHERE u.email = :pemail")
	User findByLogin(@Param("pemail") String email);

}
