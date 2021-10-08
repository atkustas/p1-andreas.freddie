package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {

	LoginService ls = new LoginService();

	//works in Postman
	public Handler loginHandler = (ctx) -> {

		String body = ctx.body();

		Gson gson = new Gson();

		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);

		if (ls.login(LDTO.getUsername(), LDTO.getPassword())) { // if login is successful

			// generate JSON Web Token (JWT) to uniquely identify user
			String jwt = JwtUtil.generate(LDTO.getUsername(), LDTO.getPassword());

			// create user session
			ctx.req.getSession(); // req is "Request Object" to establish session thru

			// successful status code
			ctx.status(200);

			ctx.result("Login successful. JWT is: " + jwt);
			
			

		} else {
			ctx.status(401); // unauthorized status code
			ctx.result("Login failed.");
		}
	};
	
	UserService as = new UserService();

	/*
	 * public Handler updateUserHandler = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//parse JSON to ticket
			User allUser = ctx.bodyAsClass(User.class);
			
			//push ticket object through Service to DAO
			as.updateUser(allUser);
			
			//report success
			ctx.status(200);
			
		} else {
			ctx.status(403);
		}
		
	};
	 * */

	public Handler getUserHandler = (ctx) -> {

		if(ctx.req.getSession(false) != null) {
			//int temp = temp.getRole_id();
		User allUser = null;//as.getUserRole(ID);
			//mak a list
		//search through that list to match user log in
		//update tole id into user that log in
		Gson gson = new Gson();

		String JSONUser = gson.toJson(allUser);

		ctx.result(JSONUser);

		ctx.status(200);

	} else {
		ctx.status(403);
	}

};

	public Handler pageHandler = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
		String body = ctx.body();
		
		Gson gson = new Gson();
		
		LoginDTO LDTO = gson.fromJson(body, LoginDTO.class);
		
		//get user object based on credentials sent in
		List<User> user = ls.findUserByCredentials(LDTO.getUsername(), LDTO.getPassword());
		
		//convert Java user to JSON
		String JSONuser = gson.toJson(user);
		
		//send back user
		ctx.result(JSONuser);
		}
		
	};



}
