package Application.repositoriesDAO;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Application.model.Comment;
import Application.model.DisciplineProfile;

@Repository
public interface CommentDAO<T,ID extends Serializable> extends JpaRepository<Comment, Long> {

	@SuppressWarnings("unchecked")
	Comment save(Comment comment);

	//List<Comment> findbyDisciplineProfile(DisciplineProfile profile);

}
