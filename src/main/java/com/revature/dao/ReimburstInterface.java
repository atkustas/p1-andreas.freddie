package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimburstInterface {

	public void insertTicket(Reimbursement ticket);
	
	public List<Reimbursement> findAllTickets();
	
	public List<Reimbursement> findPastTicketsEmp(int id);
	
	public List<Reimbursement> findPastTicketsMang(int id);
	
	public List<Reimbursement> findTicketsByStat(int id);
	
	public List<Reimbursement> findTicketsByType(int id);

	public Reimbursement findTicketById(int id);

	public void updateTicket(Reimbursement ticket);
	
	public void updateTicketStat(Reimbursement ticket);
	
	public void updateTicketStatAPP(Reimbursement ticket);
	
	public void updateTicketStatDEN(Reimbursement ticket);
	
	
}
