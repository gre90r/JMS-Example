package de.gre90r.jmsdemo.model;

// TODO: send object over JMS
public class Email {

	private String from;
	private String to;
	private String message;

	public Email() {
		super();
	}

	public Email(String from, String to, String message) {
		super();
		this.from = from;
		this.to = to;
		this.message = message;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "Email {from=" + from + ", to=" + to + ", message=" + message + "}";
	}
	
	
}
