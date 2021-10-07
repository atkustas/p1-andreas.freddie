package com.revature.dao;

import java.util.List;

import com.revature.models.ReStatus;
import com.revature.models.ReType;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimburstInterface {

	public void insertTicket(Reimbursement ticket); //done

	public List<Reimbursement> findAllTickets(); //done

	public List<Reimbursement> findPastTicketsEmp(User user); //done

	public List<Reimbursement> findPastTicketsMang(User user); //done

	public List<Reimbursement> findTicketsByStat(ReStatus status); //done

	public List<Reimbursement> findTicketsByType(ReType type); //done

	public Reimbursement findTicketById(int id);

	public void updateTicket(Reimbursement ticket); //done

	public void updateTicketStat(Reimbursement ticket); //done

	public void updateTicketStatAPP(Reimbursement ticket);


}
