package Application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Grade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idG;
	
	@Column(name = "grade")
	private long grade;

	@OneToOne
	private User user;

	
	@ManyToOne
	private DisciplineProfile disciplineProfile;

	public Grade(long grade, User user, DisciplineProfile discipline) {
		this.setDisciplineProfile(discipline);
		this.setUser(user);
		this.setGrade(grade);
	}

	public Grade() {

	}

	public DisciplineProfile getDisciplineProfile() {
		return disciplineProfile;
	}

	public void setDisciplineProfile(DisciplineProfile disciplineProfile) {
		this.disciplineProfile = disciplineProfile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}



}
