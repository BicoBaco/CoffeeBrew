package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.TecnicoBean;
import com.mvc.bean.UtenteBean;
import com.mvc.dao.DistributoreAutomaticoDAO;
import com.mvc.dao.UtenteDAO;

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
			
		    TecnicoBean tecnico = distributoreDAO.getOccupanteTecnico(idDistributore);
		    
		    if(tecnico == null) {
		    	UtenteBean occupante = distributoreDAO.getOccupanteUtente(idDistributore);
				
				if(occupante == null) {
					response.getWriter().print("{}");
				} else {
					response.getWriter().print("{\"nome\": \"" + occupante.getNome() +
											"\", \"centesimiCredito\": " + occupante.getCentesimiCredito() +
											  ", \"idUtente\": " + occupante.getIdUtente() + 
											  ", \"isTecnico\": false}");
					System.out.println("{\"nome\": \"" + occupante.getNome() +
									"\", \"centesimiCredito\": " + occupante.getCentesimiCredito() +
									  ", \"idUtente\": " + occupante.getIdUtente() + 
									  ", \"isTecnico\": false}");
				}
		    } else {
		    	response.getWriter().print("{\"nome\": \"" + tecnico.getNome() +
										"\", \"idTecnico\": " + tecnico.getIdTecnico() +
		    							  ", \"isTecnico\": true}");
		    	System.out.println("{\"nome\": \"" + tecnico.getNome() +
								"\", \"idTecnico\": " + tecnico.getIdTecnico() +
		    					  ", \"isTecnico\": true}");
		    }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("arrivata la post con: " + request.getParameter("idDistributore"));
		
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			
			if(request.getParameter("exit") != null) {
				try {
					System.out.println("Sto liberando " + idDistributore);
					distributoreDAO.liberaDistributore(idDistributore);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.setContentType("text/plain");
			    response.setCharacterEncoding("UTF-8");
				response.getWriter().write("liberato");
			}
			
			if(request.getParameter("idUtente") != null && request.getParameter("idUtente") != "" &&
			   request.getParameter("importo") != null && request.getParameter("importo") != "") {
				
				int idUtente = Integer.parseInt(request.getParameter("idUtente"));
				int importo = Integer.parseInt(request.getParameter("importo"));
				
				UtenteBean utente = new UtenteBean();
				utente.setIdUtente(idUtente);
				
				try {
					distributoreDAO.impostaLiberoUtente(idDistributore);
					UtenteDAO.rimuoviCredito(utente, importo);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("liberato distributore " + idDistributore);
				
				response.setContentType("text/plain");
			    response.setCharacterEncoding("UTF-8");
				response.getWriter().write("acquisto fatto");
			}
		}
	}
}
