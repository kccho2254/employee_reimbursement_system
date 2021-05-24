package com.revature.controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.revature.models.Reimb;
import com.revature.services.ReimbDAO;
import com.revature.utilities.ConnectionUtil;
 	
@JsonFormat(pattern = "dd-MM-yyyy")
@JsonDeserialize(using = LocalDateDeserializer.class)
@WebServlet("/api/Reimbursements")
public class ReimbursementPendingApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3238121499771291987L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		ReimbDAO db = ConnectionUtil.getReimbDAO();
		List<Reimb> reim = db.viewAllTickets();
		

		try (PrintWriter pw = response.getWriter();) {
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			pw.write(objectMapper.writeValueAsString(reim));
		}
	}
}
