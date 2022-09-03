package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.UtenteBean;
import com.mvc.bean.TecnicoBean;
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

		if(request.getSession().getAttribute("utente") != null || request.getSession().getAttribute("tecnico") != null) {		
			if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
				int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
				
				try {
					if(!distributoreDAO.isOccupato(idDistributore)) {
						if(request.getSession().getAttribute("tecnico") != null) {
							TecnicoBean tecnico = (TecnicoBean) request.getSession().getAttribute("tecnico");
							distributoreDAO.impostaOccupatoTecnico(idDistributore, tecnico.getIdTecnico());
							
						} else if (request.getSession().getAttribute("utente") != null) {
							UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
							distributoreDAO.impostaOccupatoUtente(idDistributore, utente.getIdUtente());
						}	
						response.getWriter().print("Connesso");
						
					} else {
						response.getWriter().print("Occupato");
					}
				} catch (SQLException e) {
					if(e.getMessage() == "Distributore non esistente") {
						response.getWriter().print("Il distributore "+idDistributore+" non esiste");
					} else {
						e.printStackTrace();
						response.getWriter().print("Errore, contattare l'amministratore");
					}
				}
			} else {
				response.getWriter().print("Id mancante");
			}
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
