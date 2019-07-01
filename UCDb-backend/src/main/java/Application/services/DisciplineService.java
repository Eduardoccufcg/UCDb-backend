package Application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import Application.model.Discipline;
import Application.repositoriesDAO.DisciplineDAO;

@Service
public class DisciplineService {

	private final DisciplineDAO disciplineDAO;

	private final ProfileService profileService;

	public DisciplineService(DisciplineDAO disciplineDAO, ProfileService profileService) {
		this.profileService = profileService;
		this.disciplineDAO = disciplineDAO;
	}

	public Iterable<Discipline> create(Iterable<Discipline> discipline) {
		disciplineDAO.saveAll(discipline);
		for (Discipline d : disciplineDAO.findAll()) {
			profileService.create(d);
		}
		return disciplineDAO.saveAll(discipline);

	}
	public List<Discipline> findBySubstring(String substring) {

		if (substring.isEmpty()) {
			return new ArrayList<>();
		}

		return this.disciplineDAO.findBySubstring(substring);

	}

}