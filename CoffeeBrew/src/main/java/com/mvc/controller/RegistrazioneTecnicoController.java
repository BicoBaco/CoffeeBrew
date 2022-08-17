package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.TecnicoBean;
import com.mvc.dao.TecnicoDAO;

/**
 * Servlet implementation class RegistrazioneTecnicoController
 */
public class RegistrazioneTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneTecnicoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("nome") != null && request.getParameter("nome") != "" &&
		   request.getParameter("cognome") != null && request.getParameter("cognome") != "" &&
		   request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			TecnicoBean registrazioneTecnico = new TecnicoBean();
			registrazioneTecnico.setNome(nome);
			registrazioneTecnico.setCognome(cognome);
			registrazioneTecnico.setEmail(email);
			registrazioneTecnico.setPassword(password);
			
			boolean result;
			result = TecnicoDAO.registraTecnico(registrazioneTecnico);
			
			if(result) request.getSession(true).setAttribute("tecnico", registrazioneTecnico);
			//TODO else ERROR PAGE
			
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
	}

}
