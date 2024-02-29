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
 * Servlet implementation class DeleteShippingServlet
 */
@WebServlet("/DeleteShippingServlet")
public class DeleteShippingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medTypeCode = request.getParameter("medTypeCode");
		String weightRange = request.getParameter("weightRange");
		PharmaDaoImpl delete = new PharmaDaoImpl();
		HttpSession session = request.getSession();
		try {
			boolean delFlag = delete.delShippingMaster(medTypeCode, weightRange);
			if (delFlag) {
				session.setAttribute("succMsg",
						"Shipping Master: " + medTypeCode + " and " + weightRange + "  deleted Successfully");
				response.sendRedirect("ShippingChargeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("ShippingChargeServlet");
			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}

}
