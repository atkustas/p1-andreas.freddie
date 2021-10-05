package com.revature.services;

import com.revature.dao.UserDao;
import com.revature.models.User;

public class LoginService {

	// instantiate user DAO
	UserDao ud = new UserDao();

	public boolean login(String username, String password) {

		// check db for username
		User user = ud.userLogin(username);

		// check that user password in database matches input
		if (user.getUsername() == username && user.getPassword() == password) {

			return true;

		}

		return false;

	}

}
