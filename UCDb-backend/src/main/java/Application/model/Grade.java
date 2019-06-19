package Application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_grade;
	@OneToOne
	@JoinColumn(name = "email_user")
	private User user;
	@ManyToOne
	@JoinColumn(name = "id_profile")
	private Profile profile;

	private int grade;

	public Grade(int grade) {
		this.setGrade(grade);

	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
