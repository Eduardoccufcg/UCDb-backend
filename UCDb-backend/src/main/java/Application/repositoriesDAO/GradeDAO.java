package Application.repositoriesDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Application.model.DisciplineProfile;
import Application.model.Grade;
import Application.model.User;

public interface GradeDAO extends JpaRepository<Grade, Long> {


	@Query(value = "SELECT g.grade FROM Grade g WHERE g.disciplineProfile = :id")
	List<Long> findGradesByPerfil(@Param("id") long idDisciplineProfile);


	 @Query(
	 value=("SELECT g FROM Grade g WHERE g.user = :user and g.disciplineProfile = :dis"))
	 Grade findByPK(@Param("user") User user,@Param("dis") DisciplineProfile dis);
	 
}
