package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.bean.CartaDiCreditoBean;
import com.mvc.bean.UtenteBean;
import com.mvc.dao.CartaDiCreditoDAO;

/**
 * Servlet implementation class CarteDiCreditoController
 */
public class CarteDiCreditoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarteDiCreditoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("utente") != null) {
			
			UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
			ArrayList<CartaDiCreditoBean> listaCarte = null;
			
			try {
				listaCarte = CartaDiCreditoDAO.getCarte(u.getIdUtente());
			} catch (SQLException e) {
				response.sendRedirect("landing.jsp?error=Errore del database");
			}
			
			request.setAttribute("listaCarte", listaCarte);		
			request.getRequestDispatcher("/WEB-INF/gestioneCarteDiCredito.jsp").forward(request,response);
		} else {
			response.sendRedirect("AccessoUtenteController");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("numeroCarta") != null && request.getParameter("numeroCarta") != "" &&
		   request.getParameter("nomeSullaCarta") != null && request.getParameter("nomeSullaCarta") != "" && 
		   request.getParameter("dataScadenza") != null && request.getParameter("dataScadenza") != "") {
			
			String numeroCarta = request.getParameter("numeroCarta");
			String nomeSullaCarta = request.getParameter("nomeSullaCarta");
			Date dataScadenza = Date.valueOf(request.getParameter("dataScadenza"));
			
			CartaDiCreditoBean cartaDiCredito = new CartaDiCreditoBean();
			cartaDiCredito.setNumeroCarta(numeroCarta);
			cartaDiCredito.setNomeSullaCarta(nomeSullaCarta);
			cartaDiCredito.setDataScadenza(dataScadenza);
			UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
			cartaDiCredito.setIdUtente(u.getIdUtente());
		
			try {
				CartaDiCreditoDAO.inserisciCarta(cartaDiCredito);
			} catch (SQLException e) {
				//TODO va bene così?
				response.sendRedirect("CarteDiCreditoController?error=Errore nell'inserimento#carte");
			}
			
			response.sendRedirect("CarteDiCreditoController#carte");	
		} else {
			response.sendRedirect("CarteDiCreditoController?error=Riempire tutti i campi#carte");
		}
	}

}
