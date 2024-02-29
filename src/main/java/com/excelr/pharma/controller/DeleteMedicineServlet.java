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
 * Servlet implementation class DeleteMedicineServlet
 */
@WebServlet("/DeleteMedicineServlet")
public class DeleteMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String medCode = request.getParameter("medCode");
		PharmaDaoImpl delete = new PharmaDaoImpl();
		HttpSession session = request.getSession();
		try {
			boolean delFlag = delete.removeMedicineByMedicineCode(medCode);
			if (delFlag) {
				session.setAttribute("succMsg", "Medicine Code: " + medCode + " is Deleted Successfully");
				response.sendRedirect("MedicineServlet");
			} else {
				session.setAttribute("failedMsg", "Something Error On Server");
				response.sendRedirect("MedicineServlet");

			}
		} catch (PharmaException e) {
			e.printStackTrace();
		}
	}
}
