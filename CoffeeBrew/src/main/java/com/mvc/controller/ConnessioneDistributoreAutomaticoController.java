package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.UtenteBean;
import com.mvc.dao.DistributoreAutomaticoDAO;

/**
 * Servlet implementation class ConnessioneDistributoreAutomaticoController
 */
public class ConnessioneDistributoreAutomaticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DistributoreAutomaticoDAO distributoreDAO = new DistributoreAutomaticoDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnessioneDistributoreAutomaticoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO check login
		
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			
			UtenteBean occupante = distributoreDAO.getOccupante(idDistributore);
			if(occupante == null) {
				UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
				distributoreDAO.impostaOccupato(idDistributore, utente.getIdUtente());
				
				response.getWriter().print("macchina libera, connessione in corso...");
			} else {
				response.getWriter().print("macchina non disponibile");
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
