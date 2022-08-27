package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.UtenteBean;
import com.mvc.dao.UtenteDAO;

/**
 * Servlet implementation class AccessoUtenteController
 */

public class AccessoUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoUtenteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/accessoUtente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UtenteBean accessoUtente = new UtenteBean();
			accessoUtente.setEmail(email);
			accessoUtente.setPassword(password);
			
			boolean result = false;
			try {
				result = UtenteDAO.accediUtente(accessoUtente);
			} catch (SQLException e) {
				response.sendRedirect("AccessoUtenteController?error=Errore del database");
			}
			
			if(result) {
				request.getSession().invalidate();
				request.getSession(true).setAttribute("utente", accessoUtente);
				request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			} else {
				response.sendRedirect("AccessoUtenteController?error=Email o password errata");
			}
		}
	}

}
