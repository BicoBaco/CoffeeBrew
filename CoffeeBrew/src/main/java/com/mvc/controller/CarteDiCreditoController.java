package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.bean.CartaDiCreditoBean;
import com.mvc.bean.UtenteBean;
import com.mvc.dao.CartaDiCreditoDAO;
import com.mvc.dao.UtenteDAO;

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
			int centesimiCredito = 0;
			
			try {
				listaCarte = CartaDiCreditoDAO.getCarte(u.getIdUtente());
				centesimiCredito = UtenteDAO.getCentesimiCredito(u);
			} catch (SQLException e) {
				response.sendRedirect("landing.jsp?error=Errore del database");
			}
			
			request.setAttribute("listaCarte", listaCarte);		
			request.setAttribute("centesimiCredito", centesimiCredito);
			request.getRequestDispatcher("/WEB-INF/gestioneCarteDiCredito.jsp").forward(request,response);
		} else {
			response.sendRedirect("AccessoUtenteController");
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
