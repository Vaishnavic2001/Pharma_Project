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
 * Servlet implementation class DeleteMedTypeServlet
 */
@WebServlet("/DeleteMedTypeServlet")
public class DeleteMedTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medTypeCode = request.getParameter("medTypeCode");
		PharmaDaoImpl delete = new PharmaDaoImpl();
		HttpSession session = request.getSession();
		try {
			boolean delFlag = delete.delMedType(medTypeCode);
			if (delFlag) {
				session.setAttribute("succMsg", "Medicine Type: " + medTypeCode + "  is deleted Successfully");
				response.sendRedirect("MedicineTypeServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("MedicineTypeServlet");

			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}

}
