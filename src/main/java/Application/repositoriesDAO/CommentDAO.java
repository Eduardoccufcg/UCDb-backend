package Application.repositoriesDAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Application.model.Comment;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Long> {

}
