package Application.exception;

import java.util.Date;

public class CustomRestError {
	
	private Date date;
	private String message;
	private String description;

	public CustomRestError(Date date, String message, String description) {
		this.date = date;
		this.message = message;
		this.description = description;
	}

}
