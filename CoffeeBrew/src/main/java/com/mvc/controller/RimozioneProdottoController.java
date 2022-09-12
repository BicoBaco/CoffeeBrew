package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.dao.ProdottoDAO;

/**
 * Servlet implementation class RimozioneProdottoController
 */
public class RimozioneProdottoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneProdottoController() {
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
		if(request.getSession().getAttribute("amministratore") == null) {
			response.sendRedirect("AccessoAmministratoreController?error=Accesso non autorizzato senza autenticazione");
		} else {
			String temp = request.getParameter("idProdotto");
			
			if(temp == null)
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore nella rimozione del prodotto#prodotti");
			
			int idProdotto = Integer.parseInt(temp);
			
			try {
				ProdottoDAO.rimuoviProdotto(idProdotto);
			} catch (SQLException e) {
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore del database#prodotti");
			}
			response.sendRedirect("PannelloDiControlloAmministratoreController#prodotti");
		}
	}

}