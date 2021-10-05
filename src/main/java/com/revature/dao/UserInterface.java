package com.revature.dao;

import java.util.List;


import com.revature.models.User;

public interface UserInterface {
	
	public void insertUser(User emp);
	
	public List<User> findAllUsers();
	
	public User userLogin(String username);
}
