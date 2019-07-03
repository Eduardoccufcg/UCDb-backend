package Application.exception;

import java.util.Date;

public class CustomRestError {
	
	private Date date;
	private String message;
	private String description;

	public CustomRestError(Date date, String message, String description) {
		this.setDate(date);
		this.setMessage(message);
		this.setDescription(description);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
