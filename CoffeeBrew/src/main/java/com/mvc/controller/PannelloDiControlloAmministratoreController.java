package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.bean.TecnicoBean;
import com.mvc.bean.UtenteBean;
import com.mvc.dao.DistributoreAutomaticoDAO;
import com.mvc.dao.TecnicoDAO;
import com.mvc.dao.UtenteDAO;

/**
 * Servlet implementation class PannelloDiControlloAmministratoreController
 */
public class PannelloDiControlloAmministratoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PannelloDiControlloAmministratoreController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("amministratore") != null) {
			ArrayList<DistributoreAutomaticoBean> listaDistributori = null;
			ArrayList<TecnicoBean> listaTecnici = null;
			ArrayList<UtenteBean> listaUtenti = null;
			
			try {
				listaDistributori = DistributoreAutomaticoDAO.getDistributori();
				listaTecnici = TecnicoDAO.getTecnici();
				listaUtenti = UtenteDAO.getUtenti();
			} catch (SQLException e) {
				//TODO redirect a qualcosa
			}
			
			request.setAttribute("listaDistributori", listaDistributori);
			request.setAttribute("listaTecnici", listaTecnici);
			request.setAttribute("listaUtenti", listaUtenti);
			request.getRequestDispatcher("/WEB-INF/pannelloDiControlloAmministratore.jsp").forward(request, response);
		} else {
			response.sendRedirect("AccessoAmministratoreController");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
