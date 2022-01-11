package com.java16classroom.model;

import java.util.ArrayList;
import java.util.List;
import com.java16classroom.model.User;

public class GameRecord {
	private List<User> users = new ArrayList<>();

	public List<User> getUsers() {
		if(this.users == null) {
			return new ArrayList<>() ;
			}		
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		if(this.users == null) {
			return  ;
		}	
		users.add(user);
;	}
}
