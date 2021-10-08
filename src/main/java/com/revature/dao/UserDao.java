package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDao implements UserInterface {

	public void insertUser(User emp) {
		// TODO Auto-generated method stub

		// open session object to establish connection to DB
		Session ses = HibernateUtil.getSession();

		ses.save(emp);

		HibernateUtil.closeSession();

	}

	@Override
	public List<User> findAllUsers() {

		Session ses = HibernateUtil.getSession();

		// using HQL, references Java Classes, not DB entities
		List<User> userList = ses.createQuery("FROM User").list();

		HibernateUtil.closeSession();
		// cant close session after return statment but error might occur
		// memeory could leak too but no too bad in this case
		// lazy is bad change to eager

		return userList;
	}

	@Override
	public User userLogin(String username) {
		//updated anf need to be pushed
		User temp = new User();
		
		System.out.println("========================================");
		System.out.println("logging finduser by name");
		System.out.println(findUserByName(username));
		temp=findUserByName(username);
		System.out.println("logging user pulled");
		System.out.println(temp);
		System.out.println("========================================");
		
		int tempID = temp.getId();
		
		// open connection
		Session ses = HibernateUtil.getSession();

		// get user
		User user = ses.get(User.class, tempID);

		// close connection
		HibernateUtil.closeSession();

		return user;

	}

	@Override
	public User findUserByName(String username) {
		//updated anf need to be pushed
		//User temp = new User();
		int tempID = 0;
		
		
				//retrieve users from database
				List<User> allUser = findAllUsers();
				System.out.println(username);
				for (User u : allUser) {
					System.out.println(u);
					Boolean areEqual = 	username.equals(u.getUsername());
					if(areEqual) 
					{
						System.out.println("===================================");
						
						User temp = u;
						
						System.out.println(temp);
						
						tempID=temp.getId();
						
						System.out.println("===================================");
						
					}
					//System.out.println(temp);
				}
				
			
		
		Session ses = HibernateUtil.getSession();

		User ticket = ses.get(User.class, tempID);
		
		System.out.println(ticket);

		HibernateUtil.closeSession();

		return ticket;
	}
	

	@Override
	public User findUserByID(int id) {
		//updated anf need to be pushed
		//User temp = new User();
		int tempID = 0;
		
		
				//retrieve users from database
				List<User> allUser = findAllUsers();
				System.out.println(id);
				for (User u : allUser) {
					System.out.println(u);
					Boolean areEqual = 	id == u.getId();
					if(areEqual) 
					{
						System.out.println("===================================");
						
						User temp = u;
						
						System.out.println(temp);
						
						tempID=temp.getId();
						
						System.out.println("===================================");
						
					}
					//System.out.println(temp);
				}
				
			
		
		Session ses = HibernateUtil.getSession();

		User ticket = ses.get(User.class, tempID);
		
		System.out.println(ticket);

		HibernateUtil.closeSession();

		return ticket;
	}
	

	
	@Override
	public User getUserRole(int ID) {
		// TODO Auto-generated method stub
		User user = findUserByID(ID);
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByCredentials(String username, String password) {
		
		Session ses = HibernateUtil.getSession();
		
		List<User> user = ses.createQuery("FROM User WHERE username = '" + username + "' AND password = '" + password + "'").list();
		
		HibernateUtil.closeSession();
		
		return user;
	}
	
}
