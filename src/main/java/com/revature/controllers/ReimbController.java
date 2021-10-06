package com.revature.controllers;

import java.util.List;

import com.google.gson.Gson;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;

import io.javalin.http.Handler;

public class ReimbController {
	
	ReimbService rServ = new ReimbService();
	
	public Handler allTicketsHandler = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//get Reimbursement data call Service Layer that calls DAO method
			List<Reimbursement> allTickets = rServ.allTickets();
			
			//instantiate Gson
			Gson gson = new Gson();
			
			//convert Java to JSON String
			String JSONTickets = gson.toJson(allTickets);
			
			//return results with successful status code
			ctx.result(JSONTickets);
			ctx.status(200);
			
		} else {
			ctx.status(403); //forbidden status
		}
		
		
	};

}
