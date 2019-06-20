package Application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Application.model.DisciplineProfile;
import Application.repositoriesDAO.DisciplineProfileDAO;

@Service
public class DisciplineProfileService {

	private final DisciplineProfileDAO disciplineProfileDAO;

	public DisciplineProfileService(DisciplineProfileDAO disciplineProfileDAO) {
		this.disciplineProfileDAO = disciplineProfileDAO;
	}

	public Iterable<DisciplineProfile> create(Iterable<DisciplineProfile> disciplineProfile) {
	
		return disciplineProfileDAO.saveAll(disciplineProfile);
	}
	public DisciplineProfile getProfile(long id) {
		return disciplineProfileDAO.findById(id);
	}

	public Object getAverageProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> findBySubstring(String substring) {
		return disciplineProfileDAO.findBySubstring(substring);
	}

}
