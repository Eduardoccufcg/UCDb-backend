package Application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.model.Comment;
import Application.model.Discipline;

import Application.model.Profile;
import Application.model.RankingDTO;
import Application.model.RankingDTOList;
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

	public RankingDTOList rankingTop10() {
		//
		List<RankingDTO> list1 = new ArrayList<>();
		List<Profile> profiles = profileDAO.profileByLikes();
		for (int i = 0; i < 10; i++) {
			Profile p = profiles.get(i);
			list1.add(new RankingDTO(p.getId(), p.getDiscipline().getName(), p.getNumLikes()));
		}
		
		
		//
		List<RankingDTO> list2 = new ArrayList<>();
		List<Profile> profiles2 = profileDAO.profileByComments();
		for (int i = 0; i < 10; i++) {
			Profile p = profiles2.get(i);
			list2.add(new RankingDTO(p.getId(), p.getDiscipline().getName(), p.getNumComments()));
		}
		
		RankingDTOList result = new RankingDTOList(list1, list2);
		return result;

	}

}
