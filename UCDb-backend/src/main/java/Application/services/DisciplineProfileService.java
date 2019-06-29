package Application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.model.Comment;
import Application.model.DisciplineProfile;
import Application.model.User;
import Application.repositoriesDAO.CommentDAO;
import Application.repositoriesDAO.DisciplineProfileDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class DisciplineProfileService {

	private final DisciplineProfileDAO disciplineProfileDAO;
	private final UserDAO userDAO;

	@Autowired
	private CommentDAO<?, ?> commentDAO;

	public DisciplineProfileService(DisciplineProfileDAO disciplineProfileDAO, UserDAO userDAO) {

		this.disciplineProfileDAO = disciplineProfileDAO;
		this.userDAO = userDAO;
	}

	public Iterable<DisciplineProfile> create(Iterable<DisciplineProfile> disciplineProfile) {

		return disciplineProfileDAO.saveAll(disciplineProfile);
	}

	public DisciplineProfile getProfile(long id, String email) {
		User user = userDAO.findByLogin(email);
		DisciplineProfile discipline = disciplineProfileDAO.findById(id);

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

		return disciplineProfileDAO.save(discipline);
	}

	public List<DisciplineProfile> findBySubstring(String substring) {
		return disciplineProfileDAO.findBySubstring(substring);
	}

	/*
	 * Dar like
	 */
	public DisciplineProfile toLike(String email, long idProfile) {

		User user = userDAO.findByLogin(email);
		DisciplineProfile discipline = disciplineProfileDAO.findById(idProfile);
		if (!discipline.userThatGaveLike().contains(user)) {
			discipline.userThatGaveLike().add(user);
		} else {
			discipline.userThatGaveLike().remove(user);
		}
		discipline.setNumLikes(discipline.userThatGaveLike().size());
		return disciplineProfileDAO.save(discipline);

	}

	/* Comentar */
	public DisciplineProfile toComment(long id, String email, Comment comment) {
		User u = this.userDAO.findByLogin(email);
		DisciplineProfile d = this.disciplineProfileDAO.findById(id);

		if (d != null && u != null) {
			comment.setProfile(d);
			comment.setUser(u);
			comment.setDate(new Date());
			this.commentDAO.save(comment);
			List<Comment> l = commentDAO.findbyDisciplineProfile(d);

			d.setComments(l);

			return this.disciplineProfileDAO.save(d);
		} else {
			// .....
			throw new IllegalArgumentException();
		}

	}

}
