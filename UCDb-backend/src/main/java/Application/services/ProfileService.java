package Application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.model.Comment;
import Application.model.Discipline;
import Application.model.Profile;
import Application.model.User;
import Application.repositoriesDAO.CommentDAO;
import Application.repositoriesDAO.DisciplineDAO;
import Application.repositoriesDAO.ProfileDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class ProfileService {

	private final ProfileDAO profileDAO;
	private final UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;

	public ProfileService(ProfileDAO disciplineProfileDAO, UserDAO userDAO, DisciplineDAO disciplinaDAO) {

		this.profileDAO = disciplineProfileDAO;
		this.userDAO = userDAO;

	}

	public Profile create(Discipline discipline) {
		Profile p = new Profile(discipline.getId());
		p.setDiscipline(discipline);
		return profileDAO.save(p);
	}

	public Profile getProfile(long id, String email) {
		User user = userDAO.findByLogin(email);
		Profile discipline = profileDAO.findById(id);

		if (discipline.userThatGaveLike().contains(user)) {
			discipline.setUserLogInLike(true);
		} else {
			discipline.setUserLogInLike(false);
		}
		List<Comment> list = commentDAO.findbyDisciplineProfile(discipline);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser().equals(user)) {
				list.get(i).setUserLogInComment(true);
			} else {
				list.get(i).setUserLogInComment(false);
			}
		}

		return profileDAO.save(discipline);
	}

	/*
	 * Dar like
	 */
	public Profile toLike(String email, long idProfile) {

		User user = userDAO.findByLogin(email);
		Profile discipline = profileDAO.findById(idProfile);
		if (!discipline.userThatGaveLike().contains(user)) {
			discipline.userThatGaveLike().add(user);
		} else {
			discipline.userThatGaveLike().remove(user);
		}
		discipline.setNumLikes(discipline.userThatGaveLike().size());
		return profileDAO.save(discipline);

	}

	/* Comentar */
	public Profile toComment(long id, String email, Comment comment) {
		User u = this.userDAO.findByLogin(email);
		Profile d = this.profileDAO.findById(id);

		if (d != null && u != null) {
			comment.setProfile(d);
			comment.setUser(u);
			comment.setDate(new Date());
			comment.setDeleted(false);
			this.commentDAO.save(comment);
			List<Comment> l = commentDAO.findbyDisciplineProfile(d);

			d.setComments(l);

			return this.profileDAO.save(d);
		} else {
			// .....
			throw new IllegalArgumentException();
		}

	}
	/*
	 * Responder um comentario
	 */

	public Profile toReplyComment(long idParent, String email, Comment comment) {
		Comment parent = commentDAO.findByIdComment(idParent);
		comment.setDate(new Date());
		Profile profile = parent.getProfile();
		comment.setProfile(profile);
		comment.setParent(parent);
		comment.setDeleted(false);
		comment.setUser(this.userDAO.findByLogin(email));
		commentDAO.save(comment);
		parent.getAnswers().add(comment);
		return this.profileDAO.save(profile);
	}

	public Comment toDeleteComment(long idComment) {
		Comment comment = commentDAO.findByIdComment(idComment);

		comment.setDeleted(true);

		commentDAO.save(comment);

		Profile profile = comment.getProfile();
		this.profileDAO.save(profile);
		return comment;
	}

}
