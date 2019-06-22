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

@Entity
public class DisciplineProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	
	private double nota;
	
	private boolean userLike;

	@OneToMany
	private List<Comment> comments;

	@OneToMany
	private List<Grade> grades;

	@ManyToMany
	@JoinTable(name = "user_like_discipline", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "email"))
	private List<User> userThatGaveLike;

	public DisciplineProfile() {

	}

	public DisciplineProfile(String name) {
		this.setNota(0);
		this.userLike = false;
		this.setName(name);
		this.setComments(new ArrayList<Comment>());
		this.setGrades(new ArrayList<Grade>());

	}
	public int getLikes() {
		if(this.userThatGaveLike == null) return 0;
		else
			return this.userThatGaveLike.size();
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

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public boolean isUserLike() {
		return userLike;
	}

	public void setUserLike(boolean userLike) {
		this.userLike = userLike;
	}
	
}
