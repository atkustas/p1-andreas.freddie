package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {

	LoginService ls = new LoginService();

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

}