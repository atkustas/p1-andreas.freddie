package com.revature;


import org.hibernate.HibernateException;
import org.hibernate.Session;
//import com.revature.utils.HibernateUtil;

//import com.revature.utils.ConnectionUtil;
//import com.revature.controllers.LoginController;

import java.sql.Connection;
import java.sql.SQLException;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		System.out.println("wh");
		
		
		//MovieDao md = new MovieDao();
		//LoginController lc = new LoginController();
/*
		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("hello, connection was successful!");
		} catch (SQLException e) {
			System.out.println("connection failed!");
			e.printStackTrace();
		}
*/

		
		/*
		try (Session ses = HibernateUtil.getSession()) {

			System.out.println("Hello you have a Connection to your DB with Hibernate!");
			HibernateUtil.closeSession();//could leave this connection test in, close the session within the try/catch
		} catch (HibernateException e) {
			System.out.println("DB connection has failed!!");
		}
		*/
		
		Javalin app = Javalin.create(

				config -> {
					config.enableCorsForAllOrigins();// allows the sever to process JS request from anywhere
				}

		).start(8090);

		// GET /avengers => return all avengers
		//app.get("/avengers", ac.getAllAvengersHandler);

		//app.post("/login", lc.loginHandler);
		
		

		
	}
	
	
	
	
	//This is simply a placeholder, feel free to delete it
	
	/*
	  
	 here's a dog. good luck!
	 
	  
	      __      _
		o'')}____//
		 `_/      )
		 (_(_/-(_/
			  
	 
	 */
	
}
