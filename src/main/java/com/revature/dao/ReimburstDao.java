package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimburstDao implements ReimburstInterface {

	@Override
	public void insertTicket(Reimbursement ticket) {
		
		//open session object to estiblish connection to DB
		Session ses = HibernateUtil.getSession();
		
		ses.save(ticket);
		
		HibernateUtil.closeSession();
		
	}

	@Override
	public List<Reimbursement> findAllTickets() {
		Session ses = HibernateUtil.getSession();
		
		//using HQL, references Java Classes, not DB entities
		List<Reimbursement> ticketList = ses.createQuery("FROM Reimbursement").list();
		
		HibernateUtil.closeSession(); 
		//cant close session after return statment but error might occur
		//memeory could leak too but no too bad in this case
		//lazy is bad change to eager
		
		return ticketList;
	}

	@Override
	public void addNewTicket(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reimbursement> findPastTicketsEmp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findPastTicketsMang() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findTicketsByStat() {
		
		return null;
	}

	@Override
	public List<Reimbursement> findTicketsByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement findTicketById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Reimbursement ticket = ses.get(Reimbursement.class, id);
			
			HibernateUtil.closeSession();
			
			return ticket;
	}

	@Override
	public void updateTicket(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTicketStat(Reimbursement ticket) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction(); //update and delete must happen within a transaction
		
		//updates and deletes take a little more work... You should put the query into a Query object
		//and then make sure to executeUpdate(), similar to in JDBC.
		
		//Assign the Query syntax to a String
		//String HQL = "UPDATE Movie SET title = '" + movie.getTitle() + "' WHERE id = " + movie.getId();
		
		//Assign the Query syntax to a String
		String HQL = "UPDATE Reimbursement SET re_status_id = '" + ticket.getRe_status_id() + "' WHERE id = " + ticket.getRe_id();
		
		//Instantiate a Query object with createQuery()
		Query q = ses.createQuery(HQL);
		
		//Send the update to the DB just like JDBC
		q.executeUpdate();
		
		//close transaction and session to prevent memory leak
		tran.commit();
		HibernateUtil.closeSession();
		
	}

	@Override
	public void updateTicketStatAPP(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTicketStatDEN(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
	}

}
