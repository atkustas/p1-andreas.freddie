package com.revature;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
//import com.revature.utils.HibernateUtil;

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

		// MovieDao md = new MovieDao();
		// LoginController lc = new LoginController();

		UserRoleDao urDao = new UserRoleDao();
		UserDao uDao = new UserDao();
		/*
		 * try (Connection conn = ConnectionUtil.getConnection()) {
		 * System.out.println("hello, connection was successful!"); } catch
		 * (SQLException e) { System.out.println("connection failed!");
		 * e.printStackTrace(); }
		 */

		try (Session ses = HibernateUtil.getSession()) {

			System.out.println("Hello you have a Connection to your DB with Hibernate!");
			HibernateUtil.closeSession();// could leave this connection test in, close the session within the try/catch
		} catch (HibernateException e) {
			System.out.println("DB connection has failed!!");
		}

		Javalin app = Javalin.create(

				config -> {
					config.enableCorsForAllOrigins();// allows the sever to process JS request from anywhere
				}

		).start(8090);

		// GET /avengers => return all avengers
		// app.get("/avengers", ac.getAllAvengersHandler);

		// app.post("/login", lc.loginHandler);

		// ***************************************(Test CRUD Commands)

		// creating some directors
		UserRoles mang = new UserRoles("Manager");
		UserRoles emp = new UserRoles("Employee");

		ReimburstDao rbDao = new ReimburstDao();
		ReStatusDao rsDao = new ReStatusDao();
		ReTypeDao rtDao = new ReTypeDao();
		// filmgraphy flied will get populated

		// creating some movies
		User emp1 = new User("Dawn", "Action", "Fred", "Smith", "hello@gmail.com", emp);
		User mang1 = new User("Pulp", "Drama", "Andres", "Micheal", "revature@gmail.com", mang);
		User emp2 = new User("Velvet", "Thriller", "Nick", "Jones", "LegoFan@yahoo.com", emp);

		// insert our new movies into the database
		uDao.insertUser(emp1);
		uDao.insertUser(mang1);
		uDao.insertUser(emp2);
		// mDao.insertMovie(m1);
		// mDao.insertMovie(m2);

		// retrieve our Movies from the DB
		List<User> allUSer = uDao.findAllUsers();

		for (User u : allUSer) {
			System.out.println(u);
		}

		// **********************************(reimburstment test)

		// creating some directors
		ReStatus good = new ReStatus("Approved");
		ReStatus bad = new ReStatus("Denied");

		ReType pass = new ReType("Pending");
		ReType approved = new ReType("Seen");
		// filmgraphy flied will get populated

		// creating some movies
		Reimbursement ticket1 = new Reimbursement(15, "noon", "next-day", "Fred's ticket on food", null, emp1, mang1,
				good, approved);
		Reimbursement ticket2 = new Reimbursement(35, "11:15", "n/a", "Andres's ticket on food", null, emp2, null, bad,
				pass);

		// insert our new movies into the database
		rbDao.insertTicket(ticket1);
		rbDao.insertTicket(ticket2);

		// retrieve our Movies from the DB
		List<Reimbursement> allTickets = rbDao.findAllTickets();

		for (Reimbursement t : allTickets) {
			System.out.println(t);
		}

		System.out.println("\n\n**********************************");
		System.out.println("\nPrint Ticket by ID");
		System.out.println("\n\n**********************************");

		// finding movie by id
		System.out.println(rbDao.findTicketById(2));

		System.out.println("\n\n**********************************");
		System.out.println("\nUpdate NEW Movie Title by ID");
		System.out.println("\n\n**********************************");

		// updating movie
		// m1.setTitle("OOGABOOGABOOGABOOGA");
		ticket2.setRe_status_id(good);
		ticket2.setFlieds(1, 1, "12:50", mang1, good, approved);
		// to aprove or deny a ticket what needs to be changed/updated?

		// mDao.updateMovie2(m1);
		// rbDao.updateTicketStat(ticket2);
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
