package com.excelr.pharma.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excelr.pharma.vo.AdminVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// String name = "Admin";
		HttpSession session = request.getSession();

		if ("admin@admin.com".equals(email)) {
			if ("admin".equals(password)) {
				AdminVO admin = new AdminVO();
				admin.setName("Admin");
				admin.setEmail(email);
				admin.setPassword(password);
				session.setAttribute("adminObj", admin);
				response.sendRedirect("index.jsp");
			} else {
				session.setAttribute("failedMsg", "Password Incorrect");
				response.sendRedirect("login.jsp");
			}
		} else {
			session.setAttribute("failedMsg", "Email Id Incorrect");
			response.sendRedirect("login.jsp");
		}

	}

}
