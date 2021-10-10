package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.ReimburstDao;
import com.revature.models.ReStatus;
import com.revature.models.ReType;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbService {
	
	ReimburstDao rDao = new ReimburstDao();
	Logger log = LogManager.getLogger(ReimbService.class);
	
	public List<Reimbursement> allTickets(){
		
		log.info("All reimbursement tickets were viewed.");
		
		return rDao.findAllTickets();
	}
	
	public void insertTicket(Reimbursement ticket) {
		
		log.info("New reimbursement ticket added to database.");
		
		rDao.insertTicket(ticket);
	}
	
	public void updateTicket(Reimbursement ticket) {
		
		log.info("Ticket #" + ticket.getRe_id() + " updated in database.");
		
		rDao.updateTicket(ticket);
	}
	
	public void updateTicketStat(Reimbursement ticket) {
		
		rDao.updateTicketStat(ticket);
	}
	
	public List<Reimbursement> findPastTicketsEmp(User user){
		
		log.info(user.getFirstName() +" "+ user.getLastName() + " viewed their reimbursement tickets.");
		
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
	
	public void updateTicketStatAPP(Reimbursement ticket) {
		
		log.info("Manager updated ticket #" +ticket.getRe_id() + " status.");
		
		rDao.updateTicketStatAPP(ticket);
	}
	
	public Reimbursement findTicketById(int id) {
		
		return rDao.findTicketById(id);
	}

}
