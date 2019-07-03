package Application.repositoriesDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Application.model.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Long> {
	
	@Query(value="SELECT p FROM Profile p WHERE p.name LIKE %:subs%")
	List<Profile> findBySubstring(@Param("subs") String subs);

	@Query(value = "SELECT p FROM Profile p ORDER BY p.numLikes DESC,p.id ASC")
	List<Profile> profileByLikes();

	@Query(value = "SELECT p FROM Profile p ORDER BY p.numComments DESC,p.id ASC")
	List<Profile> profileByComments();

	Profile findById(long id);

	@SuppressWarnings("unchecked")
	Profile save(Profile p);
}
