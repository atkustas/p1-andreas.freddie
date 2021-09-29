package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDao implements UserInterface {

	public void insertUser(User emp) {
		// TODO Auto-generated method stub
		
				//open session object to estiblish connection to DB
				Session ses = HibernateUtil.getSession();
				
				ses.save(emp);
				
				HibernateUtil.closeSession();
				
		
	}

	@Override
	public List<User> findAllUsers() {
		
	Session ses = HibernateUtil.getSession();
		
		//using HQL, references Java Classes, not DB entities
		List<User> userList = ses.createQuery("FROM User").list();
		
		HibernateUtil.closeSession(); 
		//cant close session after return statment but error might occur
		//memeory could leak too but no too bad in this case
		//lazy is bad change to eager
		
		return userList;
	}

}
