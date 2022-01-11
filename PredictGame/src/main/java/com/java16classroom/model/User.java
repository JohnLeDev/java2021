package com.java16classroom.model;

public class User {
	private String name;
	private int score = 1000;

	public String getName() {
		if (this.name == null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore() {
		this.score = this.score - 1;
	}

	public void reset() {
		this.score = 1000;
		this.name = "";
	}

}
