package Application.services;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public CommentService() {

	}

	public Comment toComment(long id, ServletRequest request, Comment comment) {
		User user = this.userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = this.profileDAO.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Usuário não existe");
		}
		if (profile == null) {
			throw new ProfileNotFoundException("Perfil não existe");
		}
		profile.setNumComments(profile.getNumComments() + 1);
		comment.setProfile(profile);
		comment.setUser(user);
		comment.setDate(new Date());
		comment.setDeleted(false);
		this.commentDAO.save(comment);
		List<Comment> CommentsList = commentDAO.findbyDisciplineProfile(profile);
		profile.setComments(CommentsList);
		this.profileDAO.save(profile);
		
		return comment;

	}
	
	public Comment toReplyComment(long idParent, ServletRequest request, Comment comment) {
		
		Comment parent = commentDAO.findByIdComment(idParent);
		comment.setDate(new Date());
		Profile profile = parent.getProfile();
		comment.setProfile(profile);
		comment.setParent(parent);
		comment.setDeleted(false);
		User user = this.userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		comment.setUser(user);
		commentDAO.save(comment);
		parent.getAnswers().add(comment);
		profile.setNumComments(profile.getNumComments() + 1);
		this.profileDAO.save(profile);
		return comment;
	}

	public Comment toDeleteComment(long idComment) {
		Comment comment = commentDAO.findByIdComment(idComment);
		Profile profile = comment.getProfile();
		if (comment.getParent() == null) {
			profile.setNumComments(profile.getNumComments() - 1);
		}
		comment.setDeleted(true);

		int numChildrens = deleteChildrens(comment.getAnswers());
		profile.setNumComments(profile.getNumComments() - numChildrens);
		commentDAO.save(comment);
		this.profileDAO.save(profile);
		return comment;
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
