package com.revature.controllers;

import java.util.List;
import com.google.gson.Gson;
import com.revature.models.ReStatus;
import com.revature.models.ReType;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbService;

import io.javalin.http.Handler;

public class ReimbController {
	
	ReimbService rServ = new ReimbService();
	
	//works in Postman
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
	
	//works in Postman
	public Handler insertTicketHandler = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//make ticket object from JS
			Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
		
			
			//push ticket object through Service to DAO
			rServ.insertTicket(ticket);
			
			//report successful status code
			ctx.status(201);
			
		} else {
			ctx.status(403);
		}
		
	};
	
	//works in Postman
	public Handler updateTicketHandler = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//parse JSON to ticket
			Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
			
			//push ticket object through Service to DAO
			rServ.updateTicket(ticket);
			
			//report success
			ctx.status(200);
			
		} else {
			ctx.status(403);
		}
		
	};
	
	public Handler updateTicketStat = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
		//parse JSON to ticket
		Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
		
		//push ticket through Service to DAO
		rServ.updateTicketStat(ticket);
		
		//report success
		ctx.status(200);
		
		} else {
			ctx.status(403);
		}
	};
	
	//works in Postman
	public Handler findPastTicketsEmp = (ctx) -> {
		
		if (ctx.req.getSession(false)!= null) {
			
			//parse JSON to User
			User user = ctx.bodyAsClass(User.class);
			
			//send user to Service then DAO
			List<Reimbursement> userTickets = rServ.findPastTicketsEmp(user);
			
			//instantiate Gson
			Gson gson = new Gson();
			
			//convert Java to JSON
			String tkts = gson.toJson(userTickets);
			
			//return result
			ctx.result(tkts);
			ctx.status(200);
			
			
		} else {
			ctx.status(403);
		}
		
	};
	
	public Handler findPastTicketsMang = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//parse JSON
			User user = ctx.bodyAsClass(User.class);
			
			//send user to Service then DAO
			List<Reimbursement> userTickets = rServ.findPastTicketsMang(user);
			
			//Gson
			Gson gson = new Gson();
			
			//convert Java to JSON
			String tkts = gson.toJson(userTickets);
			
			//return result
			ctx.result(tkts);
			ctx.status(200);
			
		} else {
			ctx.status(403);
		}
		
	};
	
	//NOT TESTED IN POSTMAN
	public Handler findTicketsByStat = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//parse JSON
			ReStatus status = ctx.bodyAsClass(ReStatus.class);
			
			//send ticket to Service then DAO
			List<Reimbursement> ticketsByStat = rServ.findTicketsByStat(status);
			
			//GSON
			Gson gson = new Gson();
			
			//convert Java to JSON
			String tkts = gson.toJson(ticketsByStat);
			
			//result
			ctx.result(tkts);
			ctx.status(200);
			
		} else {
			ctx.status(403);
		}
		
	};
	
	//NOT TESTED IN POSTMAN
	public Handler findTicketsByType = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
			//parse JSON
			ReType type = ctx.bodyAsClass(ReType.class);
			
			//send type to Service then DAO
			List<Reimbursement> ticketsByType = rServ.findTicketsByType(type);
			
			//GSON
			Gson gson = new Gson();
			
			//convert Java to JSON
			String tkts = gson.toJson(ticketsByType);
			
			//result
			ctx.result(tkts);
			ctx.status(200);
			
		} else {
			ctx.status(400);
		}
		
	};
	
	//works in Postman
	public Handler updateTicketMang = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
		//parse JSON to ticket
		Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
		
		//push ticket through Service to DAO
		rServ.updateTicketStat(ticket);
		
		//report success
		ctx.status(200);
		
		} else {
			ctx.status(403);
		}
		
	};
	
	public Handler approveDeny = (ctx) -> {
		
		if(ctx.req.getSession(false)!= null) {
			
		//parse JSON to ticket
		Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
		
		//push ticket through Service to DAO
		rServ.updateTicketStatAPP(ticket);
		
		//report success
		ctx.status(200);
		
		} else {
			ctx.status(403);
		}
		
	};
	
	public Handler viewTicketById = (ctx) -> {
		
		if(ctx.req.getSession(false) != null) {
			
		Reimbursement ticket = ctx.bodyAsClass(Reimbursement.class);
		
		rServ.findTicketById(ticket.getRe_id());
		
		ctx.status(200);
			
		} else {
			ctx.status(403);
		}
	};


}
