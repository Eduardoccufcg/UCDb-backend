package Application.repositoriesDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Application.model.Discipline;



@Repository
public interface DisciplineDAO extends JpaRepository<Discipline, String> {
	
	@Query(value="SELECT * FROM Discipline WHERE name LIKE %:subs%",nativeQuery = true)
	List<Discipline> findBySubstring(@Param("subs") String subs);

	Discipline findById(long id);
}
