package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.dao.DistributoreAutomaticoDAO;

/**
 * Servlet implementation class RimozioneDistributoreAutomaticoController
 */
public class RimozioneDistributoreAutomaticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneDistributoreAutomaticoController() {
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
		
		String temp = request.getParameter("idDistributore");
		
		if(temp == null)
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore nella rimozione del distributore");
		
		int idDistributore = Integer.parseInt(temp);
		
		try {
			DistributoreAutomaticoDAO.rimuoviDistributoreAutomatico(idDistributore);
		} catch (SQLException e) {
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore del database");
		}
		response.sendRedirect("PannelloDiControlloAmministratoreController#distributori");
	}

}
