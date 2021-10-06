package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class LoginService {

	// instantiate user DAO
	UserDao ud = new UserDao();

	public boolean login(String username, String password) {
//updated anf need to be pushed
		// check db for username
		User user = ud.userLogin(username);
		
				Boolean areEqual = 	username.equals(user.getUsername());
				Boolean areEqual2 = password.equals(user.getPassword());

		// check that user password in database matches input
		if (areEqual && areEqual2) {

			return true;

		}

		return false;

	}

}
