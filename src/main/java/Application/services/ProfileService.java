package Application.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.exception.ProfileNotFoundException;
import Application.exception.UserNotFoundException;
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

	@Autowired
	private ProfileDAO profileDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private TokenParseEmail tokenParse;

	public ProfileService() {

	}

	public Iterable<Profile> create(Iterable<Profile> profiles) {
		return profileDAO.saveAll(profiles);
	}

	public Profile getProfile(long id, ServletRequest request) {
		User user = userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = profileDAO.findById(id);
		if (user == null) {
			throw new UserNotFoundException("Usuário não existe");
		}
		if (profile == null) {
			throw new ProfileNotFoundException("Perfil não existe");
		}

		if (profile.userThatGaveLike().contains(user)) {
			profile.setUserLogInLike(true);
		} else {
			profile.setUserLogInLike(false);
		}

		userLogInComment(commentDAO.findbyDisciplineProfile(profile), user);

		return profileDAO.save(profile);
	}

	private void userLogInComment(List<Comment> list, User user) {
		
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getUser().equals(user)) {

				list.get(i).setUserLogInComment(true);
			} else {

				list.get(i).setUserLogInComment(false);
			}
		}
	}

	public Profile toLike(ServletRequest request, long id) {

		User user = userDAO.findByLogin(tokenParse.tokenParseEmail(request));
		Profile profile = profileDAO.findById(id);

		if (user == null) {
			throw new UserNotFoundException("Usuário não existe");
		}
		if (profile == null) {
			throw new ProfileNotFoundException("Perfil não existe");
		}
		if (!profile.userThatGaveLike().contains(user)) {
			profile.userThatGaveLike().add(user);
		} else {
			profile.userThatGaveLike().remove(user);
		}
		profile.setNumLikes(profile.userThatGaveLike().size());
		return profileDAO.save(profile);

	}

	public RankingDTOList rankingTop10() {

		List<RankingDTO> list1 = new ArrayList<>();
		List<RankingDTO> list2 = new ArrayList<>();
		List<Profile> profilesLikes = profileDAO.profileByLikes().subList(0, 10);
		List<Profile> profilesComments = profileDAO.profileByComments().subList(0, 10);

		for (Profile profile : profilesLikes) {
			list1.add(new RankingDTO(profile.getId(), profile.getName(), profile.getNumLikes()));

		}
		for (Profile profile : profilesComments) {
			list2.add(new RankingDTO(profile.getId(), profile.getName(), profile.getNumComments()));

		}

		return new RankingDTOList(list1, list2);

	}

	public List<SubjectDTO> findBySubstring(String substring) {

		List<SubjectDTO> list = new ArrayList<>();

		if (!substring.isEmpty()) {
			for (Profile profile : profileDAO.findBySubstring(substring)) {
				list.add(new SubjectDTO(profile.getId(), profile.getName()));
			}
		}

		return list;

	}

}
