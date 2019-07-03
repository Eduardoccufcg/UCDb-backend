package Application.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idComment;

	@ManyToOne
	@JsonBackReference(value = "profile")
	private Profile profile;

	@ManyToOne
	private User user;

	@Transient
	private boolean userLogInComment;

	private String text;

	private Date date;

	@ManyToOne
	@JsonBackReference(value = "parent")
	private Comment parent;

	@OneToMany
	private List<Comment> answers;

	private boolean deleted;

	public Comment() {

	}

	public Comment(String text, Profile profile, User user) {
		this.setProfile(profile);
		this.setUser(user);
		this.text = text;
		this.date = new Date();

	}

	public String getText() {
		if (deleted == true)
			return "";
		else
			return text;
	}

	public void setText(String text) {

		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserLogInComment() {
		return userLogInComment;
	}

	public void setUserLogInComment(boolean userLogInComment) {
		this.userLogInComment = userLogInComment;
	}

	public List<Comment> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Comment> answers) {
		this.answers = answers;
	}

	public long getIdComment() {
		return idComment;
	}

	public void setIdComment(long idComment) {
		this.idComment = idComment;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

}
