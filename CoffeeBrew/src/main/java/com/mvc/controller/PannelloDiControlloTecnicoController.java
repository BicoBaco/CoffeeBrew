package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.dao.DistributoreAutomaticoDAO;

/**
 * Servlet implementation class PannelloDiControlloTecnicoController
 */
public class PannelloDiControlloTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PannelloDiControlloTecnicoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("tecnico") != null) {
			ArrayList<DistributoreAutomaticoBean> listaDistributori = null;
			
			try {
				listaDistributori = DistributoreAutomaticoDAO.getDistributori();
			} catch (SQLException e) {
				//TODO redirect a qualcosa
			}
			
			request.setAttribute("listaDistributori", listaDistributori);
			request.getRequestDispatcher("/WEB-INF/pannelloDiControlloTecnico.jsp").forward(request, response);
		} else {
			response.sendRedirect("AccessoTecnicoController");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
