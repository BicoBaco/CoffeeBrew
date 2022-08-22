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
 * Servlet implementation class RegistrazioneUtenteController
 */

public class RegistrazioneUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneUtenteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/registrazioneUtente.jsp").forward(request, response);
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
			
			if(password.length() < 8) response.sendRedirect("RegistrazioneUtenteController?error=Password minimo 8 caratteri");
			
			UtenteBean registrazioneUtente = new UtenteBean();
			registrazioneUtente.setNome(nome);
			registrazioneUtente.setCognome(cognome);
			registrazioneUtente.setEmail(email);
			registrazioneUtente.setPassword(password);
			
			try {
				UtenteDAO.registraUtente(registrazioneUtente);
			} catch (SQLException e) {
				// TODO gestire registrazione con stessa email con errore adeguato
				response.sendRedirect("RegistrazioneUtenteController?error=Errore del database");
			}
			
			request.getSession(true).setAttribute("utente", registrazioneUtente);
			response.sendRedirect("home.jsp");
		}
	}

}
