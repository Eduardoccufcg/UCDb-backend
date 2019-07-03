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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Profile {

	@Id
	@JoinColumn(name = "id_profile")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private int numLikes;
	@JsonIgnore
	private int numComments;

	@OneToMany
	private List<Comment> comments;

	@Transient
	private boolean userLogInLike;

	@ManyToMany
	@JoinTable(name = "user_like_discipline", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "email"))
	private List<User> userThatGaveLike;

	public Profile() {

	}

	public Profile(String name) {

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

	public int getNumComments() {
		return numComments;
	}

	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
