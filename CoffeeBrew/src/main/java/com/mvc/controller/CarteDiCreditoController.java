package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
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
			System.out.println(" -> e sei loggato");
			UtenteBean u = (UtenteBean) request.getSession().getAttribute("utente");
			ArrayList<CartaDiCreditoBean> listaCarte = CartaDiCreditoDAO.getCarte(u.getIdUtente());
			
			request.setAttribute("listaCarte", listaCarte);
			System.out.println(" -> lista carte");
			System.out.println(listaCarte.toString());
			
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
			
			boolean result;
			result = CartaDiCreditoDAO.inserisciCarta(cartaDiCredito);
			
			/*
			if(result) request.getSession(true).setAttribute("utente", registrazioneUtente);
			//TODO else ERROR PAGE
			*/
			
			response.sendRedirect("CarteDiCreditoController");	
		} else {
			System.out.println("beccato senza dati");
			response.sendRedirect("CarteDiCreditoController?error=Riempire tutti i campi");
		}
	}

}
