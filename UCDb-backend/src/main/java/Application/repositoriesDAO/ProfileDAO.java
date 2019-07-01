package Application.repositoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import Application.model.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Long> {

	Profile findById(long id);

	@SuppressWarnings("unchecked")
	Profile save(Profile p);
}
