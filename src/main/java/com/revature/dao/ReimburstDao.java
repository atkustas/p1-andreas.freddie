package com.revature.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimburstDao implements ReimburstInterface {

	@Override
	public void insertTicket(Reimbursement ticket) {

		// open session object to estiblish connection to DB
		Session ses = HibernateUtil.getSession();

		ses.save(ticket);

		HibernateUtil.closeSession();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> findAllTickets() {
		Session ses = HibernateUtil.getSession();

		// using HQL, references Java Classes, not DB entities
		List<Reimbursement> ticketList = ses.createQuery("FROM Reimbursement").list();

		HibernateUtil.closeSession();
		// cant close session after return statement but error might occur
		// memory could leak too but no too bad in this case
		// lazy is bad change to eager

		return ticketList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> findPastTicketsEmp(int id) {

		Session ses = HibernateUtil.getSession();

		List<Reimbursement> empReimb = ses.createQuery("FROM Reimbursement where re_author = " + id).list();

		HibernateUtil.closeSession();

		return empReimb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> findPastTicketsMang(int id) {
		Session ses = HibernateUtil.getSession();

		List<Reimbursement> empReimb = ses.createQuery("FROM Reimbursement where re_author = " + id).list();

		HibernateUtil.closeSession();

		return empReimb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> findTicketsByStat(int id) {

		Session ses = HibernateUtil.getSession();

		List<Reimbursement> empReimb = ses.createQuery("FROM Reimbursement where re_status_id = " + id).list();

		HibernateUtil.closeSession();

		return empReimb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reimbursement> findTicketsByType(int id) {

		Session ses = HibernateUtil.getSession();

		List<Reimbursement> empReimb = ses.createQuery("FROM Reimbursement where re_type_id = " + id).list();

		HibernateUtil.closeSession();

		return empReimb;
	}

	@Override
	public Reimbursement findTicketById(int id) {
		Session ses = HibernateUtil.getSession();

		Reimbursement ticket = ses.get(Reimbursement.class, id);

		HibernateUtil.closeSession();

		return ticket;
	}

	@Override
	public void updateTicket(Reimbursement ticket) {

		Session ses = HibernateUtil.getSession();

		Transaction tran = ses.beginTransaction();

		// assign query to String
		String HQL = "UPDATE Reimbursement Set re_amount = '" + ticket.getRe_amount() + "'WHERE re_id = "
				+ ticket.getRe_id();

		// make query object from HQL
		Query q = ses.createQuery(HQL);

		// send update to database
		q.executeUpdate();

		// close transaction & session
		tran.commit();
		HibernateUtil.closeSession();

	}

	@Override
	public void updateTicketStat(Reimbursement ticket) {
		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction(); // update and delete must happen within a transaction

		// updates and deletes take a little more work... You should put the query into
		// a Query object
		// and then make sure to executeUpdate(), similar to in JDBC.

		// Assign the Query syntax to a String
		// String HQL = "UPDATE Movie SET title = '" + movie.getTitle() + "' WHERE id =
		// " + movie.getId();

		// Assign the Query syntax to a String
		String HQL = "UPDATE Reimbursement SET re_status_id = '" + ticket.getRe_status_id() + "' WHERE id = "
				+ ticket.getRe_id();

		// Instantiate a Query object with createQuery()
		Query q = ses.createQuery(HQL);

		// Send the update to the DB just like JDBC
		q.executeUpdate();

		// close transaction and session to prevent memory leak
		tran.commit();
		HibernateUtil.closeSession();

	}

	@Override
	public void updateTicketStatAPP(Reimbursement ticket) {
		// ticket.setRe_status_id(1);

		Session ses = HibernateUtil.getSession();
		Transaction tran = ses.beginTransaction(); // update and delete must happen within a transaction

		// updates and deletes take a little more work... You should put the query into
		// a Query object
		// and then make sure to executeUpdate(), similar to in JDBC.

		// Assign the Query syntax to a String
		// String HQL = "UPDATE Movie SET title = '" + movie.getTitle() + "' WHERE id =
		// " + movie.getId();

		// Assign the Query syntax to a String
		String HQL = "UPDATE Reimbursement SET re_status_id = '" + ticket.getRe_status_id() + "',re_type_id = '"
				+ ticket.getRe_type_id() + "',re_resolver = '" + ticket.getRe_resolver() + "',re_resolved = '"
				+ ticket.getRe_resolved() + "' WHERE id = " + ticket.getRe_id();

		// Instantiate a Query object with createQuery()
		Query q = ses.createQuery(HQL);

		// Send the update to the DB just like JDBC
		q.executeUpdate();

		// close transaction and session to prevent memory leak
		tran.commit();
		HibernateUtil.closeSession();

	}

	@Override
	public void updateTicketStatDEN(Reimbursement ticket) {
		// TODO Auto-generated method stub

	}

}
