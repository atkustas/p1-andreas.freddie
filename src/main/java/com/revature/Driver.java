package com.revature;


import org.hibernate.HibernateException;
import org.hibernate.Session;
//import com.revature.utils.HibernateUtil;

import com.revature.dao.UserDao;
import com.revature.dao.UserRoleDao;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.HibernateUtil;

//import com.revature.utils.ConnectionUtil;
//import com.revature.controllers.LoginController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		System.out.println("wh");
		
		
		//MovieDao md = new MovieDao();
		//LoginController lc = new LoginController();
		
		UserRoleDao urDao = new UserRoleDao();
		UserDao uDao = new UserDao();
/*
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("hello, connection was successful!");
		} catch (SQLException e) {
			System.out.println("connection failed!");
			e.printStackTrace();
		}
*/

		
		try (Session ses = HibernateUtil.getSession()) {

			System.out.println("Hello you have a Connection to your DB with Hibernate!");
			HibernateUtil.closeSession();//could leave this connection test in, close the session within the try/catch
		} catch (HibernateException e) {
			System.out.println("DB connection has failed!!");
		}
		
		Javalin app = Javalin.create(

				config -> {
					config.enableCorsForAllOrigins();// allows the sever to process JS request from anywhere
				}

		).start(8090);

		// GET /avengers => return all avengers
		//app.get("/avengers", ac.getAllAvengersHandler);

		//app.post("/login", lc.loginHandler);
		
		
		//***************************************(Test CRUD Commands) 
		
		//creating some directors
				UserRoles mang = new UserRoles("Manager");
				UserRoles  emp = new UserRoles("Employee");
						//filmgraphy flied will get populated
						
						//creating some movies
						User emp1 = new User("Dawn", "Action", "Fred", "Smith", "hello@gmail.com", emp);
						User mang1 = new User("Pulp", "Drama", "Andres", "Micheal", "revature@gmail.com", mang);
						User emp2 = new User("Velvet", "Thriller", "Nick", "Jones", "LegoFan@yahoo.com", emp);
						
						//insert our new movies into the database
						uDao.insertUser(emp1);
						uDao.insertUser(mang1);
						uDao.insertUser(emp2);
						//mDao.insertMovie(m1);
						//mDao.insertMovie(m2);
						
						
						//retrieve our Movies from the DB
						List<User> allUSer = uDao.findAllUsers();
						
						for(User u : allUSer) {
							System.out.println(u);
						}
						

		
	}//end of main
	
	
	
	
	//This is simply a placeholder, feel free to delete it
	
	/*
	  
	 here's a dog. good luck!
	 
	  
	      __      _
		o'')}____//
		 `_/      )
		 (_(_/-(_/
			  
	 
	 */
	
}
