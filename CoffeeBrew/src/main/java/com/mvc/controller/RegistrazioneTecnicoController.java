package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.mvc.bean.TecnicoBean;
import com.mvc.dao.TecnicoDAO;

/**
 * Servlet implementation class RegistrazioneTecnicoController
 */
public class RegistrazioneTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneTecnicoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nome") != null && request.getParameter("nome") != "" &&
		   request.getParameter("cognome") != null && request.getParameter("cognome") != "" &&
		   request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(password.length() < 8) {
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Password minimo 8 caratteri");
			} else {
				TecnicoBean registrazioneTecnico = new TecnicoBean();
				registrazioneTecnico.setNome(nome);
				registrazioneTecnico.setCognome(cognome);
				registrazioneTecnico.setEmail(email);
				registrazioneTecnico.setPassword(password);
				
				try {
					TecnicoDAO.registraTecnico(registrazioneTecnico);
				} catch(Exception e) {
					if(e instanceof SQLIntegrityConstraintViolationException) {
						response.sendRedirect("PannelloDiControlloAmministratoreController?error=Email già in uso");
						return;
					}
					
					if(e instanceof SQLException) { 
						response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore del database");
					} else {
						e.printStackTrace();
					}
	
					return;
				}
			}
			response.sendRedirect("PannelloDiControlloAmministratoreController#tecnici");
		} else {
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Dati mancanti#tecnici");
		}
	}

}
