package com.revature.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimb;
import com.revature.services.ReimbDAO;
import com.revature.utilities.ConnectionUtil;

@WebServlet("/MakeNewReq")
public class MakeReimbRequestServlet extends HttpServlet {

	public void init(ServletConfig config) {
		System.out.println("init fired.");
	}

	private static final long serialVersionUID = -4999007308642431887L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		boolean isSuccess;
		ReimbDAO db = ConnectionUtil.getReimbDAO();
		Reimb reim = new Reimb();

		HttpSession session = req.getSession(false); 
		int empId = (Integer)session.getAttribute("empId"); 
		System.out.println(empId);

		reim.setReimb_amount(Float.parseFloat(req.getParameter("amount")));
		reim.setReimb_submitted(LocalDate.now());
		reim.setReimb_desc(req.getParameter("description"));
		
		reim.setReimb_author(empId);
		reim.setReimb_type(Integer.parseInt(req.getParameter("type")));
		reim.setReimb_status(1);
		// set the author to whatever the author is
//		session.setAttribute("reimbAmount", session);
//		session.setAttribute("reimbDesc", session);
//		session.setAttribute("reimbAuthor", empId);
//		
//		session.setAttribute("reimbType", session);
//		session.setAttribute("", session);
//		session.setAttribute("", session);
		System.out.println(reim);

		isSuccess = db.addNewRequest(reim);

		// check for dupes. If condUser has a hit from getAllUsers it should return a
		// value for condUser, in which case
		// it isn't null, so stop post in that case

		if (isSuccess) {
			
			System.out.println("Added a new reimbursement request");
			res.sendRedirect(req.getContextPath() + "/Employee");
		} else {
			
			System.out.println("Problem adding request");
			res.sendRedirect(req.getContextPath() + "/Employee");
		}
	}
}
