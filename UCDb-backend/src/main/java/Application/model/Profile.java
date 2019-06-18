package Application.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	
	@Id
	private int id_profile;
	
	@OneToOne
	@JoinColumn(name = "id_discipline")
	private Discipline discipline;
	
	private int numLikes;
	
	private int numDeslikes;

	
	private List<Grade> grades;
	
	public Profile() {
		
	}
	


}
