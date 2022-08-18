package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.AmministratoreBean;
import com.mvc.dao.AmministratoreDAO;

/**
 * Servlet implementation class AccessoAmministratoreController
 */
public class AccessoAmministratoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoAmministratoreController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			AmministratoreBean accessoAmministratore = new AmministratoreBean();
			accessoAmministratore.setEmail(email);
			accessoAmministratore.setPassword(password);
			
			boolean result;
			result = AmministratoreDAO.accediAmministratore(accessoAmministratore);
			
			if(result) {
				request.getSession(true).setAttribute("amministratore", accessoAmministratore);
				response.sendRedirect("pannelloDiControlloAmministratore.jsp");
			} else {
				throw new ServletException("L'amministratore inserito non esiste");
			}
		}
	}

}
