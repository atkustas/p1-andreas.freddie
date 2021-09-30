package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findTicketsByType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement findTicketById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTicket(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTicketStat(Reimbursement ticket) {
		// TODO Auto-generated method stub
		
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
