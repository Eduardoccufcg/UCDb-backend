package Application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Application.model.Discipline;
import Application.repositoriesDAO.DisciplineDAO;

@Service
public class DisciplineService {
	
	private final DisciplineDAO disciplineDAO;
	
	public DisciplineService(DisciplineDAO disciplineDAO) {
		this.disciplineDAO = disciplineDAO;
	}
	public Iterable<Discipline> create(Iterable<Discipline> discipline) {

		return disciplineDAO.saveAll(discipline);

	}
	public List<Discipline> findBySubstring(String substring) {
	
		return this.disciplineDAO.findBySubstring(substring);

	}
	

}
