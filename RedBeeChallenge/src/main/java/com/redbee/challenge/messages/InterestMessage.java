package com.redbee.challenge.messages;

/**
 * Message to the client throw Websocket
 * 
 * @author Leandro
 *
 */
public class InterestMessage {
	private String message;

	public InterestMessage() {
		
	}
	
	public InterestMessage(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
