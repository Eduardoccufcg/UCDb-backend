package Application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class DisciplineProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@OneToMany
	private List<Comment> comments;

	@OneToMany
	private List<Grade> grades;

	@Transient
	private boolean userLogInLike;

	@ManyToMany
	@JoinTable(name = "user_like_discipline", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "email"))
	private List<User> userThatGaveLike;

	public DisciplineProfile() {

	}

	public DisciplineProfile(String name) {

		this.setName(name);
		this.setComments(new ArrayList<Comment>());
		this.setGrades(new ArrayList<Grade>());

	}

	public int getLikes() {
		if (this.userThatGaveLike == null)
			return 0;
		else
			return this.userThatGaveLike.size();
	}

	public int getUserGrades() {
		if (this.grades == null)
			return 0;
		else
			return this.grades.size();
	}

	public Double getGradeProfile() {

		if (this.grades == null) {
			return 0.0;

		} else {
			Double average = 0.0;
			Double sum = 0.0;

			for (Grade grade : grades) {
				sum += grade.getGrade();
			}

			average = sum / grades.size();

			return average;

		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<User> userThatGaveLike() {
		return userThatGaveLike;
	}

	public void setUserThatGaveLike(List<User> userThatGaveLike) {
		this.userThatGaveLike = userThatGaveLike;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public int userGrades() {
		return this.grades.size();
	}

	public boolean isUserLogInLike() {
		return userLogInLike;
	}

	public void setUserLogInLike(boolean userLogIn) {
		this.userLogInLike = userLogIn;
	}

}
