package com.redbee.challenge.model;

/**
 * Interest 
 * 
 * @author Leandro
 *
 */
public class Interest {
	
	private String hashUser;
	
	public Interest() {
		
	}
	
	public Interest(String hashUser) {
		super();
		this.hashUser = hashUser;
	}
	

	public String getHashUser() {
		return hashUser;
	}
	public void setHashUser(String hashUser) {
		this.hashUser = hashUser;
	}

	@Override
	public String toString() {
		return "Interest [hashUser=" + hashUser + "]";
	}
}
