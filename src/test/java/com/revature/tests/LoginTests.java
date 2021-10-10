package com.revature.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.User;
import com.revature.services.LoginService;

public class LoginTests {

	public static LoginService ls;
	
	//variables
	public String testUsername = "Dawn";
	public String testPassword = "Action";
	
	public String testUsername2 = "yellow";
	public String testPassword2 = "banana";
	public boolean result;
	
	public List<User> result2;
	
	
	@BeforeAll
	public static void createLoginService() {
		ls = new LoginService();
		System.out.println("New LoginService created @BeforeAll");
	}
	
	@AfterAll
	public static void endLoginService() {
		ls = null;
		System.out.println("LoginService terminated @AfterAll");
	}
	
	//unit tests
	public void testLogin(String username, String password) {
		result = ls.login(testUsername, testPassword);
		assertTrue(result == true);
		
	}
	
	public void testLogin2(String username, String password) {
		result = ls.login(testUsername2, testPassword2);
		assertTrue(result == false);
	
	}

}
