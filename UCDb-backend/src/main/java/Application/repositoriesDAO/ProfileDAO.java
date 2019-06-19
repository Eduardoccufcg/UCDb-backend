package Application.repositoriesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Application.model.Profile;



public interface ProfileDAO extends JpaRepository<Profile, Long> {
	@Query(value="SELECT p FROM Profile p WHERE p.idProfile = :id")
	Profile findById(@Param("id") long id);
	
	
	//@Query(value="SELECT AVG(d.grade) FROM Profile p JOIN Grade d ON p.id_profile = d.id_profile")
	//Object getMedia(@Param("id") long id);


}
