package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimburstInterface {

	public void insertTicket(Reimbursement ticket);
	
	public List<Reimbursement> findAllTickets();
	
	
}
