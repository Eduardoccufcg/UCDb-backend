package Application.services;

import org.springframework.stereotype.Service;

import Application.model.Profile;
import Application.repositoriesDAO.DisciplineDAO;
import Application.repositoriesDAO.ProfileDAO;


@Service
public class ProfileService {
	
	
	private final ProfileDAO profileDAO;
	private final DisciplineDAO disciplineDAO;
	
	
	
	public ProfileService(ProfileDAO profileDAO,DisciplineDAO disciplinaDAO) {
		this.profileDAO = profileDAO;
		this.disciplineDAO = disciplinaDAO;
	}
	
	

	public Profile create(long id, Profile profile) {
		profile.setId(id);
		profile.setDisciplina(disciplineDAO.findById(id));
		return profileDAO.save(profile);
	}
	
	
	
	
	

}
