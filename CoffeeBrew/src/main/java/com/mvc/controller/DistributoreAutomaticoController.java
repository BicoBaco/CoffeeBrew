package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
			
			response.getWriter().write(distributoreDAO.getStato(idDistributore));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO distributore che manda il idUtente e Credito usato
		if(request.getParameter("idDistributore") != null && request.getParameter("idDistributore") != "") {
			int idDistributore = Integer.parseInt(request.getParameter("idDistributore"));
			distributoreDAO.impostaLibero(idDistributore);
			
			response.setContentType("text/plain");
		    response.setCharacterEncoding("UTF-8");
			response.getWriter().write("acquisto fatto");
		}
	}

}
