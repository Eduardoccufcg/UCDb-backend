package Application.services;

import java.util.List;

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

	public DisciplineProfile create(long id, String email, Grade grade) {

			User user = userDAO.findByLogin(email);
			DisciplineProfile discipline = profileDAO.findById(id);
			Grade aux = gradeDAO.findByPK(user, discipline);
			if(user != null && discipline != null) {
				if( aux != null) {
					aux.setGrade(grade.getGrade());
					grade = aux;
				
				}else {
					grade.setDisciplineProfile(discipline);
					grade.setUser(user);
				}
				
				gradeDAO.save(grade);
				List<Grade> list = gradeDAO.findGradesByPerfil(discipline);
				discipline.setGrades(list);
				return profileDAO.save(discipline);
			}else {
				throw new RuntimeException();
			}
			
			
	}


}
