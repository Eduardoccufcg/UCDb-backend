package Application.repositoriesDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Application.model.DisciplineProfile;



@Repository
public interface DisciplineProfileDAO extends JpaRepository<DisciplineProfile, String> {
	
	@Query(value="SELECT d FROM DisciplineProfile d WHERE d.name LIKE %:subs%")
	List<DisciplineProfile> findBySubstring(@Param("subs") String subs);

	DisciplineProfile findById(long id);
	
	
	@SuppressWarnings("unchecked")
	DisciplineProfile save(DisciplineProfile p );
}
