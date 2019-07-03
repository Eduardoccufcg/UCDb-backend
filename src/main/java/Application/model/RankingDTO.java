package Application.model;

public class RankingDTO {

	private long id;

	private String name;

	private int num;

	public long getId() {
		return id;
	}

	public RankingDTO(long id, String name, int num) {
		super();
		this.id = id;
		this.name = name;
		this.num = num;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
