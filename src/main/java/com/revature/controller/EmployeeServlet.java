package com.revature.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Using the FC Design pattern, all of the requests have been mapped to this servlet
 */
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {			
			request.getRequestDispatcher("employeePortal.html").forward(request, response);
			
		} catch (ServletException se) {
			se.printStackTrace();
		}
	}
}
