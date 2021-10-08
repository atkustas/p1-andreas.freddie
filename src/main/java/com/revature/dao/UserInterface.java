package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface UserInterface {

	public void insertUser(User emp);

	public List<User> findAllUsers();

	public User userLogin(String username);
	
	public User findUserByName(String username);
	
	public User getUserRole(int ID);

	public User findUserByID(int id);
	
	public List<User> findUserByCredentials (String username, String password);
}
