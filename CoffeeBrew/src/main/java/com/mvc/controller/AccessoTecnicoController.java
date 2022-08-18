package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.TecnicoBean;
import com.mvc.dao.TecnicoDAO;

/**
 * Servlet implementation class AccessoTecnicoController
 */
public class AccessoTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoTecnicoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			TecnicoBean accessoTecnico = new TecnicoBean();
			accessoTecnico.setEmail(email);
			accessoTecnico.setPassword(password);
			
			boolean result;
			result = TecnicoDAO.accediTecnico(accessoTecnico);
			
			if(result) {
				request.getSession(true).setAttribute("tecnico", accessoTecnico);
				response.sendRedirect("/WEB-INF/pannelloDiControlloTecnico.jsp");
			} else {
				throw new ServletException("Il tecnico inserito non esiste");
				//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Il tecnico inserito non esiste");
				//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				//response.send
			}
		}
	}

}
