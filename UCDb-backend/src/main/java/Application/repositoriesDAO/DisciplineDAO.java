package Application.repositoriesDAO;

 import java.util.List;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 import Application.model.Discipline;


 @Repository
public interface DisciplineDAO extends JpaRepository<Discipline, Long> {

 	@Query(value="SELECT p FROM Discipline p WHERE p.name LIKE %:subs%")
	List<Discipline> findBySubstring(@Param("subs") String subs);
 	
 	
 	@SuppressWarnings("unchecked")
	Discipline save (Discipline discipline);

 	Discipline findById(long id);

 }