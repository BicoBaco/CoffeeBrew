package com.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ErrorHandler
 */
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
		
		String error = "";
		Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		
		response.setContentType("text/html");	
		
		if(response.getStatus() == 401) {
			error = "Errore 401: accesso non autorizzato";
		}
		
		if(response.getStatus() == 403) {
			error = "Errore 403: accesso alla risorsa vietato";
		}
		
		if(response.getStatus() == 404) {
			error = "Errore 404: la pagina non esiste";
		}
		
		if(response.getStatus() == 500) {
			error = "Errore 500: impossibile elaborare la richiesta";
		}
		
		if(throwable != null) {
			error = "Errore interno del server, riprovare più tardi o contattare un amministratore";
		}
		
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
