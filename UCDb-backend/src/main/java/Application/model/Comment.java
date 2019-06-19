package Application.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_comment;

	@ManyToOne
	@JoinColumn(name = "id_profile")
	private Profile id_profile;

	@ManyToOne
	@JoinColumn(name = "email_user")
	private User email_user;

	private String text;

	private Date date;

	public Comment() {

	}

	public Comment(String text) {
		this.text = text;
		this.date = new Date();

	}

	public String getText() {
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

}
