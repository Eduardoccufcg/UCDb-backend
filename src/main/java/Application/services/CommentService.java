package Application.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Application.exception.CommentNotFoundException;
import Application.exception.ProfileNotFoundException;
import Application.exception.UserNotFoundException;
import Application.model.Comment;
import Application.model.Profile;
import Application.model.TokenParseEmail;
import Application.model.User;
import Application.repositoriesDAO.CommentDAO;
import Application.repositoriesDAO.ProfileDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class CommentService {

	@Autowired
	private ProfileDAO profileDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private TokenParseEmail tokenParse;

	public Comment toComment(long id, ServletRequest request, Comment comment) {
		Optional<User> userAux = this.userDAO.findById(tokenParse.tokenParseEmail(request));
		Optional<Profile> profileAux = this.profileDAO.findById(id);
		if (!userAux.isPresent()) {
			throw new UserNotFoundException("Usuário não existe");
		}

		if (!profileAux.isPresent()) {
			throw new ProfileNotFoundException("Perfil não existe");
		}
		Profile profile = profileAux.get();
		User user = userAux.get();
		comment.setProfile(profile);
		comment.setUser(user);
		Comment commentResponse = this.commentDAO.save(comment);
		profile.getComments().add(comment);
		this.profileDAO.save(profile);

		return commentResponse;

	}

	public Comment toReplyComment(long idParent, ServletRequest request, Comment comment) {

		Optional<Comment> parentAux = commentDAO.findById(idParent);
		if (parentAux.isPresent()) {
			Comment parent = parentAux.get();
			Profile profile = parent.getProfile();
			comment.setProfile(profile);
			comment.setParent(parent);
			Optional<User> userAux = this.userDAO.findById(tokenParse.tokenParseEmail(request));
			User user = userAux.get();
			comment.setUser(user);
			commentDAO.save(comment);
			parent.getAnswers().add(comment);
			profile.setNumComments(profile.getNumComments() + 1);
			this.profileDAO.save(profile);
			return comment;
		} else {
			throw new CommentNotFoundException("Comentário não encontrado");
		}
	}

	public ResponseEntity<Comment> toDeleteComment(long idComment) {
		Optional<Comment> commentAux = commentDAO.findById(idComment);
		if (commentAux.isPresent()) {
			Comment comment = commentAux.get();
			Profile profile = comment.getProfile();
			if (comment.getParent() == null) {
				profile.setNumComments(profile.getNumComments() - 1);
			}
			comment.setDeleted(true);

			int numChildrens = deleteChildrens(comment.getAnswers());
			profile.setNumComments(profile.getNumComments() - numChildrens);
			commentDAO.save(comment);
			this.profileDAO.save(profile);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			throw new CommentNotFoundException("Comentário não encontrado");
		}
	}

	private int deleteChildrens(List<Comment> list) {

		int numChildrens = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setDeleted(true);
				numChildrens++;
				deleteChildrens(list.get(i).getAnswers());
			}

		}
		return numChildrens;
	}

}
