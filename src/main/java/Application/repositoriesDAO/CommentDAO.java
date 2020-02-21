package Application.repositoriesDAO;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Application.model.Comment;
import Application.model.Profile;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {

	Comment findByIdComment(long id);	
	
	@SuppressWarnings("unchecked")
	Comment save(Comment comment);

	@Query(value = ("SELECT c FROM Comment c WHERE c.profile = :dis ORDER BY c.date DESC"))
	List<Comment> findbyDisciplineProfile(@Param("dis") Profile d);
	
		

}
