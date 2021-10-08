package com.revature.services;

import com.revature.models.User;
import com.revature.dao.UserDao;

public class UserService {

		UserDao aDao = new UserDao();

		public User getUserRole(int ID) {

		return aDao.getUserRole(ID);
	}

}
