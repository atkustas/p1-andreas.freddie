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

		//Endpoints & Handlers with who can use commented
		app.post("/login", lc.loginHandler); //all users
		app.post("/redirect", lc.pageHandler);
		app.get("/logins", lc.getUserHandler); //all users
		app.get("/alltickets", rc.allTicketsHandler); //managers
		app.post("newticket", rc.insertTicketHandler); //all users
		app.post("/userticketsemp", rc.findPastTicketsEmp); //all users
		app.get("/userticketsmang", rc.findPastTicketsMang); //managers
		app.post("/ticketbyid", rc.viewTicketById); //all users
		app.post("/approvedeny", rc.approveDeny); //managers
		
		
		//UNUSED
		app.post("/updateticketmang", rc.updateTicketMang); //managers
		app.post("updateticketstatus", rc.updateTicketStat); //managers
		app.get("/ticketsbystatus", rc.findTicketsByStat); //managers
		app.get("/ticketsbytype", rc.findTicketsByType); //managers
		app.post("/updateticketamount", rc.updateTicketHandler); //all users
		

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
		User emp3 = new User("Forceful1","iheartchewy", "Luke", "Skywalker", "bewithyou@gmail.com", emp);
		User emp4 = new User("TheWhipper", "defeateddoom", "Indiana", "Jones", "rareartifacts@gmail.com", emp);
		User mang2 = new User("BossMan", "irunthis", "Bruce", "Springsteen", "asburysfinest@gmail.com", mang);

		//put new users in database
		uDao.insertUser(emp1);
		uDao.insertUser(mang1);
		uDao.insertUser(emp2);
		uDao.insertUser(emp3);
		uDao.insertUser(emp4);
		uDao.insertUser(mang2);
		


		//retrieve users from database
		List<User> allUser = uDao.findAllUsers();

		for (User u : allUser) {
			System.out.println(u);
		}


		//creating Reimbursement Statuses
		ReStatus approved = new ReStatus("Approved");
		ReStatus denied = new ReStatus("Denied");
		ReStatus pending = new ReStatus("Pending");

		//creating Reimbursement types
		ReType business = new ReType("Business");
		ReType travel = new ReType("Travel");
		ReType medical = new ReType("Medical");
		ReType other = new ReType("Other");

		//creating reimbursement tickets
		Reimbursement ticket1 = new Reimbursement("15.00", "10/08/2021", "10/09/2021", "Fred's ticket on food", null, emp1, mang1,
				approved, other);
		Reimbursement ticket2 = new Reimbursement("35.00", "10/07/2021", "n/a", "Andres's ticket on food", null, emp2, null, denied,
				other);
		Reimbursement ticket3 = new Reimbursement("99.99", "10/06/2021", "n/a", "Gas", null, emp2, null, pending,
				travel);
		Reimbursement ticket4 = new Reimbursement("265.00", "10/06/2021", "n/a", "Lightsaber battery replacement",
				null, emp3, null, pending, business);
		Reimbursement ticket5 = new Reimbursement("489.00", "10/01/2021", "n/a", "Map to the Holy Grail", null, emp4, null,
				pending, business);
		Reimbursement ticket6 = new Reimbursement("99.99", "10/03/2021", "n/a", "Eye Exam", null, emp4, null, pending, medical);
		Reimbursement ticket7 = new Reimbursement("176.80", "10/05/2021", "n/a", "Team dinner with Han & Chewy", null, emp3, null, pending, business);
		Reimbursement ticket8 = new Reimbursement("77.16", "08/22/2021", "n/a", "Client lunch", null, emp1, null, denied, business);

		//add tickets to database
		rbDao.insertTicket(ticket1);
		rbDao.insertTicket(ticket2);
		rbDao.insertTicket(ticket3);
		rbDao.insertTicket(ticket4);
		rbDao.insertTicket(ticket5);
		rbDao.insertTicket(ticket6);
		rbDao.insertTicket(ticket7);
		rbDao.insertTicket(ticket8);

		//retrieve tickets from database
		List<Reimbursement> allTickets = rbDao.findAllTickets();

		for (Reimbursement t : allTickets) {
			System.out.println(t);
		}

		System.out.println("\n\n**********************************");
		System.out.println("\nPrint Ticket by ID");
		System.out.println("\n\n**********************************");

		//find ticket by id
		//System.out.println(rbDao.findTicketById("2"));

		System.out.println("\n\n**********************************");
		System.out.println("\nUpdate NEW Movie Title by ID");
		System.out.println("\n\n**********************************");

		//update ticket
		//ticket2.setRe_status_id(approved);
		//ticket2.setFields(1, 1, "10/11/2021", mang1, approved, other);
		

		//rbDao.updateTicketStatAPP(ticket2);

		System.out.println("\n\n**********************************");
		System.out.println("\nPrint NEW Movie Title by ID");
		System.out.println("\n\n**********************************");

		System.out.println(rbDao.findPastTicketsEmp(emp1));

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
