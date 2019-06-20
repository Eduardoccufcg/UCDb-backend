package Application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Application.model.DisciplineProfile;
import Application.model.Grade;

import Application.model.User;
import Application.repositoriesDAO.DisciplineProfileDAO;
import Application.repositoriesDAO.GradeDAO;
import Application.repositoriesDAO.UserDAO;

@Service
public class GradeService {

	private final GradeDAO gradeDAO;

	@Autowired
	private final DisciplineProfileDAO profileDAO;
	@Autowired
	private final UserDAO userDAO;

	public GradeService(GradeDAO gradeDAO, DisciplineProfileDAO profileDAO, UserDAO userDAO) {
		this.gradeDAO = gradeDAO;
		this.profileDAO = profileDAO;
		this.userDAO = userDAO;
	}

	public Grade create(long id, String email, Grade grade) {

		
		
			User user = userDAO.findByLogin(email);
			System.out.println(user.getEmail());
			DisciplineProfile discipline = profileDAO.findById(id);
			System.out.println(discipline.getId());
			grade.setDisciplineProfile(discipline);
			grade.setUser(user);

		
			return this.gradeDAO.save(grade);
	}


}
