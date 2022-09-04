package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.dao.CartaDiCreditoDAO;

/**
 * Servlet implementation class RimozioneCartaDiCreditoController
 */
public class RimozioneCartaDiCreditoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneCartaDiCreditoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("CarteDiCreditoController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("utente") == null) {
			response.sendRedirect("AccessoUtenteController?error=Accesso non autorizzato senza autenticazione");
		} else {
			String temp = request.getParameter("idCarta");
			
			if(temp == null)
				response.sendRedirect("CarteDiCreditoController?error=Errore nella rimozione della carta");
			
			int idCarta = Integer.parseInt(temp);
			
			try {
				CartaDiCreditoDAO.rimuoviCarta(idCarta);
			} catch (SQLException e) {
				response.sendRedirect("CarteDiCreditoController?error=Errore del database");
			}
			response.sendRedirect("CarteDiCreditoController");
		}
	}

}
