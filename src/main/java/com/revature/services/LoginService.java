package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginService {

	// instantiate user DAO
	UserDao ud = new UserDao();
	Logger log = LogManager.getLogger(LoginService.class);

	public boolean login(String username, String password) {
		
		// check db for username
		User user = ud.userLogin(username);
		
				Boolean areEqual = 	username.equals(user.getUsername());
				Boolean areEqual2 = password.equals(user.getPassword());

		// check that user password in database matches input
		if (areEqual && areEqual2) {
			
			log.info(user.getFirstName() +" "+ user.getLastName() + " logged in with username: " +user.getUsername() + " and password: " +user.getPassword());

			return true;

		}

		return false;

	}
	
	public List<User> findUserByCredentials(String username, String password){
		
		return ud.findUserByCredentials(username, password);
	}


}
