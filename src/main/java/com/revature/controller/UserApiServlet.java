package com.revature.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserDAO;
import com.revature.utilities.ConnectionUtil;

@WebServlet("/api/Users")
public class UserApiServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3238121499771291987L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getServletPath();
		System.out.println(requestPath);
		UserDAO db = ConnectionUtil.getUserDAO();
		List<User> users = db.getAllUsers();

		try (PrintWriter pw = response.getWriter();) {
			pw.write(new ObjectMapper().writeValueAsString(users));
		}
	}
}
