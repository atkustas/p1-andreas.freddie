package com.revature.services;

import java.util.List;

import com.revature.dao.ReimburstDao;
import com.revature.models.ReStatus;
import com.revature.models.ReType;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbService {
	
	ReimburstDao rDao = new ReimburstDao();
	
	public List<Reimbursement> allTickets(){
		
		return rDao.findAllTickets();
	}
	
	public void insertTicket(Reimbursement ticket) {
		
		rDao.insertTicket(ticket);
	}
	
	public void updateTicket(Reimbursement ticket) {
		
		rDao.updateTicket(ticket);
	}
	
	public void updateTicketStat(Reimbursement ticket) {
		
		rDao.updateTicketStat(ticket);
	}
	
	public List<Reimbursement> findPastTicketsEmp(User user){
		
		return rDao.findPastTicketsEmp(user);
	}
	
	public List<Reimbursement> findPastTicketsMang(User user){
		
		return rDao.findPastTicketsMang(user);
	}
	
	public List<Reimbursement> findTicketsByStat(ReStatus status){
		
		return rDao.findTicketsByStat(status);
	}
	
	public List<Reimbursement> findTicketsByType(ReType type){
		
		return rDao.findTicketsByType(type);
	}

}
