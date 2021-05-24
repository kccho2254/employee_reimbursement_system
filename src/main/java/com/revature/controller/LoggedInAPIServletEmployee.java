package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserDAO;
import com.revature.utilities.ConnectionUtil;

@WebServlet("/api/LoggedEmployee")
public class LoggedInAPIServletEmployee extends HttpServlet {

	// This method assigns a single instance of login info and does not write to a
	// database but should clear after application closes
	// May need another LoggedInAPIServlet but for managers. It'd be nice if I could
	// do this all in one file but I can't call sendRedirect after
	// a response has been committed. Trying to access session object that I set as
	// the email thingy

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057784595932695918L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			HttpSession session = request.getSession(false);
			String email = (String) session.getAttribute("empEmail");
			UserDAO db = ConnectionUtil.getUserDAO();
			User user = db.getEmpByEmail(email);
			// emailHTML will contain the entire employee info of whatever email is inputted

			System.out.println("Email object is this: " + user);
			System.out.println("User object is this: " + email);
			System.out.println("db is this:" + db);
			System.out.println("Session object is this " + session);

			if (email == null || session == null) {
				response.sendError(404, "No user with given ID");
			} else {
				try (PrintWriter pw = response.getWriter()) {
					pw.write(new ObjectMapper().writeValueAsString(user));

				}
			}
		} catch (IOException se) {
			se.printStackTrace();
		}
	}
}
