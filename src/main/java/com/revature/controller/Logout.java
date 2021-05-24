package com.revature.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;


@WebServlet("/Logout")
public class Logout extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 15839063666042753L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(false);
		session.invalidate();
		System.out.println("Session invalidated");
		response.sendRedirect("/projectOne");
	}
}
