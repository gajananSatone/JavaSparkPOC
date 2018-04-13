package gajanans.coreJavaPoc;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 325341692965698392L;
	private final String message;
	public Message(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
