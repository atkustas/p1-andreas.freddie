package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimburstInterface {

	public void insertTicket(Reimbursement ticket);
	
	public void addNewTicket(Reimbursement ticket);
	
	public List<Reimbursement> findPastTicketsEmp();
	
	public List<Reimbursement> findAllTickets();
	
	public List<Reimbursement> findPastTicketsMang();
	
	public List<Reimbursement> findTicketsByStat();
	
	public List<Reimbursement> findTicketsByType();

	public Reimbursement findTicketById(int id);

	public void updateTicket(Reimbursement ticket);
	
	public void updateTicketStat(Reimbursement ticket);
	
	public void updateTicketStatAPP(Reimbursement ticket);
	
	public void updateTicketStatDEN(Reimbursement ticket);
	
	
}
