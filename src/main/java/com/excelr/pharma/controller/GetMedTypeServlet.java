package com.excelr.pharma.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excelr.pharma.dao.PharmaDaoImpl;
import com.excelr.pharma.vo.MedicineTypeInfo;

/**
 * Servlet implementation class GetMedTypeServlet
 */
@WebServlet("/GetMedTypeServlet")
public class GetMedTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		try {
			PharmaDaoImpl dao = new PharmaDaoImpl();
			Set<MedicineTypeInfo> values = dao.getAllMedicineTypes();
			System.out.println(values);
			request.setAttribute("values", values);
			dispatcher = request.getRequestDispatcher("addbatch.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
