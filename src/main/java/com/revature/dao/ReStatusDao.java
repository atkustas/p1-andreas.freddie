package com.revature.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReStatus;
import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReStatusDao implements ReStatusInterface{

	@Override
	public void setStatus(ReStatus status, int id) {
		
		switch(id) {
		case 1:{
			status.setRe_status_id(id);
			status.setRe_status("Approved");
			break;
		}
		case 2:{
			status.setRe_status_id(id);
			status.setRe_status("Denied");
			break;
		}
		default:{
			status.setRe_status_id(id);
			status.setRe_status("Pending");
			break;
		}
		
		}
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(status);
		
		HibernateUtil.closeSession();
		
		

		
	}

	@Override
	public void changeStatusById(Reimbursement ticket, ReStatus status, int id) {
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tran = ses.beginTransaction();
		
		//make query into HQL String
		String HQL = "UPDATE ReStatus Set re_status_id = '" + status.getRe_status_id() + "'WHERE re_id = "
				+ id;
		
	

		// make query object from HQL
		Query q = ses.createQuery(HQL);

		// send update to database
		q.executeUpdate();

		// close transaction & session
		tran.commit();
		HibernateUtil.closeSession();
		
	}

	
	

}
