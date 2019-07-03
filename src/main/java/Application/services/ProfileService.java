package Application.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.model.Comment;

import Application.model.Profile;
import Application.model.RankingDTO;
import Application.model.RankingDTOList;
import Application.model.SubjectDTO;
import Application.model.TokenParseEmail;
import Application.model.User;
import Application.repositoriesDAO.CommentDAO;
import Application.repositoriesDAO.ProfileDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class ProfileService {

	private final ProfileDAO profileDAO;
	private final UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private TokenParseEmail tokenParse = new TokenParseEmail();

	public ProfileService(ProfileDAO profileDAO, UserDAO userDAO) {

		this.profileDAO = profileDAO;
		this.userDAO = userDAO;

	}

	public Iterable<Profile> create(Iterable<Profile> profiles) {
		return profileDAO.saveAll(profiles);
	}

	public Profile getProfile(long id, ServletRequest request) {
		User user = userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = profileDAO.findById(id);

		if (profile.userThatGaveLike().contains(user)) {
			profile.setUserLogInLike(true);
		} else {
			profile.setUserLogInLike(false);
		}
		List<Comment> list = commentDAO.findbyDisciplineProfile(profile);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUser().equals(user)) {
				list.get(i).setUserLogInComment(true);
			} else {
				list.get(i).setUserLogInComment(false);
			}
		}

		return profileDAO.save(profile);
	}

	/*
	 * Dar like
	 */
	public Profile toLike(ServletRequest request, long id) {

		User user = userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = profileDAO.findById(id);
		if (!profile.userThatGaveLike().contains(user)) {
			profile.userThatGaveLike().add(user);
		} else {
			profile.userThatGaveLike().remove(user);
		}
		profile.setNumLikes(profile.userThatGaveLike().size());
		return profileDAO.save(profile);

	}

	public RankingDTOList rankingTop10() {
		//
		List<RankingDTO> list1 = new ArrayList<>();
		List<Profile> profiles = profileDAO.profileByLikes();
		for (int i = 0; i < 10; i++) {
			Profile p = profiles.get(i);
			list1.add(new RankingDTO(p.getId(), p.getName(), p.getNumLikes()));
		}

		//
		List<RankingDTO> list2 = new ArrayList<>();
		List<Profile> profiles2 = profileDAO.profileByComments();
		for (int i = 0; i < 10; i++) {
			Profile p = profiles2.get(i);
			list2.add(new RankingDTO(p.getId(), p.getName(), p.getNumComments()));
		}

		RankingDTOList result = new RankingDTOList(list1, list2);
		return result;

	}

	public List<SubjectDTO> findBySubstring(String substring) {

		if (substring.isEmpty()) {
			return new ArrayList<>();
		} else {
			List<SubjectDTO> list = new ArrayList<>();
			for (Profile profile : profileDAO.findBySubstring(substring)) {
				list.add(new SubjectDTO(profile.getId(),profile.getName()));

			}
			return list;

		}

	}

}
