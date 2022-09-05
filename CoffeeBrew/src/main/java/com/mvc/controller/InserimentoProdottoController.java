package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.ProdottoBean;
import com.mvc.dao.ProdottoDAO;

/**
 * Servlet implementation class InserimentoProdottoController
 */
public class InserimentoProdottoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoProdottoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("PannelloDiControlloAmministratoreController#prodotti");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("nome") != null && request.getParameter("nome") != "" &&
			request.getParameter("costo") != null && request.getParameter("costo") != "") {
			
			String nome = request.getParameter("nome");
			int costo = Integer.parseInt(request.getParameter("costo"));
			
			ProdottoBean prodotto = new ProdottoBean();
			prodotto.setNome(nome);
			prodotto.setCosto(costo);
		
			try {
				ProdottoDAO.inserisciProdotto(prodotto);
			} catch (SQLException e) {
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore nell'inserimento#prodotti");
			}
			
			response.sendRedirect("PannelloDiControlloAmministratoreController#prodotti");	
		} else {
			response.sendRedirect("PannelloDiControlloAmministratoreController?error=Riempire tutti i campi#prodotti");
		}
	}

}
