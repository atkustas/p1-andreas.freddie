package com.revature;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
//import com.revature.utils.HibernateUtil;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbController;
import com.revature.dao.ReStatusDao;
import com.revature.dao.ReTypeDao;
import com.revature.dao.ReimburstDao;
import com.revature.dao.UserDao;
import com.revature.dao.UserRoleDao;
import com.revature.models.ReStatus;
import com.revature.models.ReType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.UserRoles;
import com.revature.utils.HibernateUtil;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Main loaded");

		//instantiations
		LoginController lc = new LoginController();
		ReimbController rc = new ReimbController();
		UserRoleDao urDao = new UserRoleDao();
		UserDao uDao = new UserDao();

		
		//open connection
		try (Session ses = HibernateUtil.getSession()) {

			System.out.println("Hello you have a Connection to your DB with Hibernate!");
			HibernateUtil.closeSession();// could leave this connection test in, close the session within the try/catch
		} catch (HibernateException e) {
			System.out.println("DB connection has failed!!");
		}

		
		//get server up
		Javalin app = Javalin.create(

				config -> {
					config.enableCorsForAllOrigins();// allows the sever to process JS request from anywhere
				}

		).start(8090);

		//function
		app.post("/login", lc.loginHandler);
		app.get("/alltickets", rc.allTicketsHandler);
		

		// ***************************************(Test CRUD Commands)

		//creating roles
		UserRoles mang = new UserRoles("Manager");
		UserRoles emp = new UserRoles("Employee");

		ReimburstDao rbDao = new ReimburstDao();
		ReStatusDao rsDao = new ReStatusDao();
		ReTypeDao rtDao = new ReTypeDao();

		//creating users
		User emp1 = new User("Dawn", "Action", "Fred", "Smith", "hello@gmail.com", emp);
		User mang1 = new User("Pulp", "Drama", "Andres", "Micheal", "revature@gmail.com", mang);
		User emp2 = new User("Velvet", "Thriller", "Nick", "Jones", "LegoFan@yahoo.com", emp);

		//put new users in database
		uDao.insertUser(emp1);
		uDao.insertUser(mang1);
		uDao.insertUser(emp2);


		//retrieve users from database
		List<User> allUser = uDao.findAllUsers();

		for (User u : allUser) {
			System.out.println(u);
		}

		// **********************************(reimburstment test)

		//creating ticket statuses
		ReStatus good = new ReStatus("Approved");
		ReStatus bad = new ReStatus("Denied");

		ReType pass = new ReType("Pending");
		ReType approved = new ReType("Seen");

		//creating reimbursement tickets
		Reimbursement ticket1 = new Reimbursement(15, "noon", "next-day", "Fred's ticket on food", null, emp1, mang1,
				good, approved);
		Reimbursement ticket2 = new Reimbursement(35, "11:15", "n/a", "Andres's ticket on food", null, emp2, null, bad,
				pass);

		//add tickets to database
		rbDao.insertTicket(ticket1);
		rbDao.insertTicket(ticket2);

		//retrieve tickets from database
		List<Reimbursement> allTickets = rbDao.findAllTickets();

		for (Reimbursement t : allTickets) {
			System.out.println(t);
		}

		System.out.println("\n\n**********************************");
		System.out.println("\nPrint Ticket by ID");
		System.out.println("\n\n**********************************");

		//find ticket by id
		System.out.println(rbDao.findTicketById(2));

		System.out.println("\n\n**********************************");
		System.out.println("\nUpdate NEW Movie Title by ID");
		System.out.println("\n\n**********************************");

		//update ticket
		ticket2.setRe_status_id(good);
		ticket2.setFlieds(1, 1, "12:50", mang1, good, approved);
		
		
		// to aprove or deny a ticket what needs to be changed/updated?

		rbDao.updateTicketStatAPP(ticket2);

		System.out.println("\n\n**********************************");
		System.out.println("\nPrint NEW Movie Title by ID");
		System.out.println("\n\n**********************************");

		System.out.println(rbDao.findTicketById(2));

	}// end of main

	// This is simply a placeholder, feel free to delete it

	/*
	 * 
	 * here's a dog. good luck!
	 * 
	 * 
	 * __ _ o'')}____// `_/ ) (_(_/-(_/
	 * 
	 * 
	 */

}
