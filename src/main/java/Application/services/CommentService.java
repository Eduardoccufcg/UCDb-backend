package Application.services;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import org.springframework.stereotype.Service;

import Application.model.Comment;
import Application.model.Profile;
import Application.model.TokenParseEmail;
import Application.model.User;
import Application.repositoriesDAO.CommentDAO;
import Application.repositoriesDAO.ProfileDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class CommentService {

	private final ProfileDAO profileDAO;
	private final UserDAO userDAO;
	private final CommentDAO commentDAO;
	private TokenParseEmail tokenParse = new TokenParseEmail();

	public CommentService(ProfileDAO profileDAO, UserDAO userDAO, CommentDAO commentDAO, TokenParseEmail tokenParse) {
		this.tokenParse = tokenParse;
		this.profileDAO = profileDAO;
		this.userDAO = userDAO;
		this.commentDAO = commentDAO;
	}

	/* Comentar */
	public Comment toComment(long id, ServletRequest request, Comment comment) {
		User u = this.userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = this.profileDAO.findById(id);
		profile.setNumComments(profile.getNumComments() + 1);
		comment.setProfile(profile);
		comment.setUser(u);
		comment.setDate(new Date());
		comment.setDeleted(false);
		this.commentDAO.save(comment);

		List<Comment> list = commentDAO.findbyDisciplineProfile(profile);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser().equals(u)) {
				list.get(i).setUserLogInComment(true);
			} else {
				list.get(i).setUserLogInComment(false);
			}
		}
		List<Comment> l = commentDAO.findbyDisciplineProfile(profile);

		profile.setComments(l);

		this.profileDAO.save(profile);
		return comment;

	}
	/*
	 * Responder um comentario
	 */

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
		List<Comment> list = commentDAO.findbyDisciplineProfile(profile);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser().equals(user)) {
				list.get(i).setUserLogInComment(true);
			} else {
				list.get(i).setUserLogInComment(false);
			}
		}
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

		deleteFilhos(comment.getAnswers());
		commentDAO.save(comment);
		this.profileDAO.save(profile);
		return comment;
	}

	private void deleteFilhos(List<Comment> list) {

		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setDeleted(true);
				deleteFilhos(list.get(i).getAnswers());
			}

		}
	}

}
