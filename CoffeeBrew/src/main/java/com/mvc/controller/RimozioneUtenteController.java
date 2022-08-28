package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.dao.UtenteDAO;

/**
 * Servlet implementation class RimozioneUtenteController
 */
public class RimozioneUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneUtenteController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("PannelloDiControlloAmministratoreController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("amministratore") == null)
			response.sendRedirect("AccessoAmministratoreController?error=Accesso non autorizzato senza autenticazione");
		
		String temp = request.getParameter("idUtente");
		
		if(temp == null)
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore nella rimozione dell'utente");
		
		int idUtente = Integer.parseInt(temp);
		
		try {
			UtenteDAO.rimuoviUtente(idUtente);
		} catch (SQLException e) {
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore del database");
		}
		response.sendRedirect("PannelloDiControlloAmministratoreController#utenti");
	}

}
