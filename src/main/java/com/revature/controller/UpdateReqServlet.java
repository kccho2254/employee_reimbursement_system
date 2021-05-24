package com.revature.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimb;
import com.revature.services.ReimbDAO;
import com.revature.utilities.ConnectionUtil;

@WebServlet("/UpdateReq")
public class UpdateReqServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6931650867133998943L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		boolean isSuccess;
		System.out.println("firing add user");
		Reimb reim = new Reimb();

		ReimbDAO db = ConnectionUtil.getReimbDAO();
		// get the manager ID from session so we can update the resolver field in SQL db
		HttpSession session = request.getSession(false);
		
		// identity of the manager logged in
		int mgrID = (Integer)session.getAttribute("managerId");
		reim.setReimb_resolver(mgrID);
		System.out.println(mgrID + " Manager ID");
		
		int reqID = Integer.parseInt(request.getParameter("reqID"));
		reim.setId(Integer.parseInt(request.getParameter("reqID")));
		System.out.println(reqID +"request ID");

		// status is num for accepted or declined
		int status = Integer.parseInt(request.getParameter("status"));
		reim.setReimb_status(Integer.parseInt(request.getParameter("status")));
		System.out.println(status +"Status ID");

		// id is the auto incremented req id
		reim.setReimb_resolved(LocalDate.now());
	

		isSuccess = db.updateRequest(reim);
//		isSuccess = db.updateRequest(LocalDate.now(), mgrID, status, reqID);
		
		if (isSuccess) {
			System.out.println("updated");
			response.sendRedirect(request.getContextPath() + "/Manager");		
		} else {
			System.out.println("Problem updating request");
			response.sendRedirect(request.getContextPath() + "/Manager");
		}
	}
}
