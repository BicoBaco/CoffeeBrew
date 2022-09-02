package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		// TODO check login
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			
			System.out.print(idDistributore);
			
			if(!distributoreDAO.isOccupato(idDistributore)) {
				if(request.getSession().getAttribute("tecnico") != null) {
					TecnicoBean tecnico = (TecnicoBean) request.getSession().getAttribute("tecnico");
					distributoreDAO.impostaOccupatoTecnico(idDistributore, tecnico.getIdTecnico());
					
					//TODO pagina che mostra macchina libera
					response.getWriter().print("Connection success");
					
				} else if (request.getSession().getAttribute("utente") != null) {
					UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
					distributoreDAO.impostaOccupatoUtente(idDistributore, utente.getIdUtente());
					
					//TODO pagina che mostra macchina libera
					response.getWriter().print("Connection success");
				}
			} else {
				response.getWriter().print("Occupied");
			}
		} else {
			response.getWriter().print("Missing id");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
