package com.redbee.challenge.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Board {
	@Id
	private String name;
	private List<Interest> interests;
	
	public Board() {
		
	}
	
	public Board(String name, List<Interest> interests) {
		super();
		this.name = name;
		this.interests = interests;
	}

	public List<Interest> getInterests() {
		return interests;
	}

	@Override
	public String toString() {
		return "Board [name=" + name + ", interests=" + interests + "]";
	}
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
