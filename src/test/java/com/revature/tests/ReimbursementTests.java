package com.revature.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

public class ReimbursementTests {

	public static ReimbService rs;
	
	//variables
	String i = "1";
	int j = 2;
	public Reimbursement result;
	
	@BeforeAll
	public static void createReimbService() {
		rs = new ReimbService();
		System.out.println("New ReimbService created @BeforeAll");
	}
	
	@AfterAll
	public static void endReimbService() {
		rs = null;
		System.out.println("ReimbService assigned null @AfterAll");
	}
	
	//unit tests
	@Test
	public void testFindTicketById(String id) {
		System.out.println("Testing finding ticket method");
		result = rs.findTicketById(i);
		assertTrue(result.getRe_id() == i);
	}
	
	
	
	
	

}
