package Application.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	@Id
	@JoinColumn(name = "id_profile")
	private long idProfile;

	@OneToOne
	@JoinColumn(name = "id_discipline")
	private Discipline discipline;

	private int numLikes;

	private int numDeslikes;

	@OneToMany
	private List<Grade> grades;

	@OneToMany
	private List<Comment> comments;

	public Profile() {

	}

	public Profile(long id) {
		this.idProfile = id;
		this.numLikes = 0;
		this.setNumDeslikes(0);
		this.comments = new ArrayList<Comment>();
		this.grades = new ArrayList<Grade>();
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public int getNumDeslikes() {
		return numDeslikes;
	}

	public void setNumDeslikes(int numDeslikes) {
		this.numDeslikes = numDeslikes;
	}
	
	public void setDisciplina(Discipline discipline) {
		this.discipline = discipline;
	}

	public long getId() {
		return idProfile;
	}

	public void setId(long id) {
		this.idProfile = id;
		
	}
	public List<Comment> getComments(){
		return this.comments;
	}

	public List<Grade> getGrades() {
		return this.grades;
	}

}
