package Application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Application.model.DisciplineProfile;
import Application.repositoriesDAO.DisciplineProfileDAO;
import Application.repositoriesDAO.GradeDAO;

@Service
public class DisciplineProfileService {

	private final DisciplineProfileDAO disciplineProfileDAO;
	private GradeDAO gradeDAO;

	public DisciplineProfileService(DisciplineProfileDAO disciplineProfileDAO, GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
		this.disciplineProfileDAO = disciplineProfileDAO;
	}

	public Iterable<DisciplineProfile> create(Iterable<DisciplineProfile> disciplineProfile) {

		return disciplineProfileDAO.saveAll(disciplineProfile);
	}

	public DisciplineProfile getProfile(long id) {
		return disciplineProfileDAO.findById(id);
	}

	public Double getAverageProfile(Long id) {
		double sum = 0;
		double average = 0;

		List<Long> grades = gradeDAO.findGradesByPerfil(id);
		for (Long grade : grades) {
			sum += grade;
		}
		if (grades.size() > 0) {
			average = sum / grades.size();
		}
		return average;

	}

	public List<DisciplineProfile> findBySubstring(String substring) {
		return disciplineProfileDAO.findBySubstring(substring);
	}

}
