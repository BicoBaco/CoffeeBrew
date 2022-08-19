package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.dao.DistributoreAutomaticoDAO;

/**
 * Servlet implementation class RegistrazioneDistributoreAutomaticoController
 */
public class RegistrazioneDistributoreAutomaticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneDistributoreAutomaticoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//TODO pannello di controllo controller
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("locazione") != null && request.getParameter("locazione") != "") {
			String locazione = request.getParameter("locazione");
			
			DistributoreAutomaticoBean registrazioneDistributoreAutomatico = new DistributoreAutomaticoBean();
			registrazioneDistributoreAutomatico.setLocazione(locazione);
			
			try {
				DistributoreAutomaticoDAO.registraDistributoreAutomatico(registrazioneDistributoreAutomatico);
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/pannelloDiControlloAmministratore.jsp?error=Errore del database");
			}
			
			response.sendRedirect("/WEB-INF/pannelloDiControlloAmministratore.jsp");
		}
	}
}
