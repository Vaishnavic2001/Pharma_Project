package com.excelr.pharma.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.exceptions.PharmaException;

/**
 * Servlet implementation class DelteBatchServlet
 */
@WebServlet("/DelteBatchServlet")
public class DelteBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String batchCode = request.getParameter("batchCode");
		PharmaDaoImpl delete = new PharmaDaoImpl();
		HttpSession session = request.getSession();
		try {
			boolean delFlag = delete.removeBatchByBacthCode(batchCode);
			if (delFlag) {
				session.setAttribute("succMsg", "Batch Code: " + batchCode + "  is deleted Successfully");
				response.sendRedirect("BatchCodeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("BatchCodeServlet");

			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}

}
