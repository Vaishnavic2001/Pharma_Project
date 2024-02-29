package com.excelr.pharma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class SearchMedTypeServlet
 */
@WebServlet("/SearchMedTypeServlet")
public class SearchMedTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String medType = request.getParameter("searchMedicineType");
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			List<MedicineTypeInfo> medTypeSearch = dao.getMedicineTypeBySearch(medType);
			if (medTypeSearch.isEmpty()) {
				request.setAttribute("noDataFound", true);
				dispatcher = request.getRequestDispatcher("viewMedicineType.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("medTypeSearch", medTypeSearch);
				dispatcher = request.getRequestDispatcher("viewMedicineType.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
