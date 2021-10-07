package com.revature.dao;

import com.revature.models.ReStatus;
import com.revature.models.Reimbursement;

public interface ReStatusInterface {
	
	public void setStatus(ReStatus status, int id);
	
	public void changeStatusById(Reimbursement ticket, ReStatus status, int id);
		
	}



	
