package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.UtenteBean;
import com.mvc.dao.DistributoreAutomaticoDAO;

/**
 * Servlet implementation class DistributoreAutomaticoController
 */
public class DistributoreAutomaticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DistributoreAutomaticoDAO distributoreDAO = new DistributoreAutomaticoDAO();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistributoreAutomaticoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
			
			UtenteBean occupante = distributoreDAO.getOccupante(idDistributore);
			if(occupante == null) {
				UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
				
				response.getWriter().print("{}");
			} else {
				response.getWriter().print("{\"nome\": \"" + occupante.getNome() + "\", \"centesimiCredito\": " + occupante.getCentesimiCredito() + "}");
				System.out.println("{\"nome\": \"" + occupante.getNome() + "\", \"centesimiCredito\": " + occupante.getCentesimiCredito() + "}");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO distributore che manda il idUtente e Credito usato
		System.out.println("arrivata la post con: " + request.getParameter("idDistributore"));
		
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			distributoreDAO.impostaLibero(idDistributore);
			
			System.out.println("liberato distributore " + idDistributore);
			
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
			response.getWriter().write("acquisto fatto");
		}
	}

}
