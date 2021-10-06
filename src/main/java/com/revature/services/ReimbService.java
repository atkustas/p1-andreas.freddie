package com.revature.services;

import java.util.List;

import com.revature.dao.ReimburstDao;
import com.revature.models.Reimbursement;

public class ReimbService {
	
	ReimburstDao rDao = new ReimburstDao();
	
	public List<Reimbursement> allTickets(){
		
		return rDao.findAllTickets();
	}

}
