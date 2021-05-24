package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) {
		System.out.println("init fired.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();  
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO db = ConnectionUtil.getUserDAO();
		User user = db.login(email, password);
		// logEmail basically checks if there is an existing email
		// if it is null, don't log in
		// if it isn't null, update all that info into the models via DAO method

		if (user != null) {
			System.out.println(user.getRoleId() + " Yay, you logged in");

			
			//if logged in user is an employee...
			if(user.getRoleId() == 1) {
				
				// try to populate html with user name
				// html is some static uncompiled format though so we have to write to JSON and run ajax to there instead
				// if this was jsp we could use setAttribute and getRequestDispatcher yade yade
//						request.setAttribute("userTagFromServlet", emailHTML);
//						request.getRequestDispatcher("employeePortal.html").forward(request, response);
				
		        session.setAttribute("empEmail", email);  
				session.setAttribute("empId", user.getId());

				// this file needs to populate JSON with the current successful login employee
		        					
				response.sendRedirect(request.getContextPath() + "/Employee");
				
				//if logged in user is a manager...
				} else if(user.getRoleId() == 2){
					HttpSession managerSession = request.getSession();  
					managerSession.setAttribute("managerEmail", email);  
					managerSession.setAttribute("managerId", user.getId());  
					response.sendRedirect(request.getContextPath() + "/Manager");
				}
		} else {
			System.out.println("Incorrect combination");
			response.sendRedirect("/projectOne");

		}

		out.close();
	}

	public void destroy() {
		System.out.println("destroy fired");
	}
}
