package Application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Profile {
	

	@OneToOne	
	private Discipline discipline;

	@Id
	@JoinColumn(name = "id_profile")
	private long id;


	private int numLikes;

	@OneToMany
	private List<Comment> comments;

	@Transient
	private boolean userLogInLike;

	@ManyToMany
	@JoinTable(name = "user_like_discipline", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "email"))
	private List<User> userThatGaveLike;

	public Profile() {

	}

	public Profile(Long id) {

		this.id = id;
		this.setComments(new ArrayList<Comment>());

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

	public boolean isUserLogInLike() {
		return userLogInLike;
	}

	public void setUserLogInLike(boolean userLogIn) {
		this.userLogInLike = userLogIn;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	@JsonBackReference
	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	public String getNameDiscipline() {
		return this.discipline.getName();
	}

}
