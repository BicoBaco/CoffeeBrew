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
import com.mvc.dao.DistributoreAutomaticoDAO;
import com.mvc.dao.TecnicoDAO;

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
			
			try {
				listaDistributori = DistributoreAutomaticoDAO.getDistributori();
				listaTecnici = TecnicoDAO.getTecnici();
			} catch (SQLException e) {
				//TODO redirect a qualcosa
			}
			
			request.setAttribute("listaDistributori", listaDistributori);
			request.setAttribute("listaTecnici", listaTecnici);
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
