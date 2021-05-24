package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.services.UserDAO;
import com.revature.utilities.ConnectionUtil;

@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {

	/**
	 * 
	 */

	public void init(ServletConfig config) {
		System.out.println("init fired.");
	}

	private static final long serialVersionUID = -4999007308642431887L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");

		UserDAO db = ConnectionUtil.getUserDAO();
		User condUser = db.getEmpByUsername(username);

		// check for dupes. If condUser has a hit from getAllUsers it should return a
		// value for condUser, in which case
		// it isn't null, so stop post in that case

		if (condUser != null) {
			System.out.println("duplicate username found");
			req.getRequestDispatcher("index.html").forward(req, res);
		} else {
			System.out.println("firing add user");
			boolean isSuccess;

			User user = new User();
			user.setEmail(req.getParameter("email"));
			user.setUsername(req.getParameter("username"));
			user.setPassword(req.getParameter("password"));
			user.setFirstName(req.getParameter("firstName"));
			user.setLastName(req.getParameter("lastName"));
			user.setRoleId(Integer.parseInt(req.getParameter("role")));

			isSuccess = db.addEmployee(user);

			System.out.println(isSuccess);

			if (isSuccess) {
				HttpSession session = req.getSession();

				System.out.println("is Sucess is true");
				if (user.getRoleId() == 1) {
					session.setAttribute("empEmail", user.getEmail());
					session.setAttribute("empId", user.getId());
					res.sendRedirect(req.getContextPath() + "/Employee");
				} else if (user.getRoleId() == 2) {
					session.setAttribute("managerEmail", user.getEmail());
					session.setAttribute("empId", user.getId());
					res.sendRedirect(req.getContextPath() + "/Manager");
				}

			} else {
				System.out.println("Problem adding user");
				res.sendRedirect("/projectOne");
			}
		}
	}
}
