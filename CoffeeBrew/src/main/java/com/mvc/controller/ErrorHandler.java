package com.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
		
		//TODO trovare come avere lo status code e le altre info
		//TODO aggiungere il lancio di eccezioni nei controller tipo nella registrazione / qualcosa per valutare i dati
		
		Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		//request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");	
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (servletName == null) servletName = "Unknown";
		if (requestUri == null)	requestUri = "Unknown";
		
		response.setContentType("text/html");
		
	    PrintWriter out = response.getWriter();
	    out.write("<html><head><title>Errore - Details</title></head><body>");
	    
	    if(throwable == null && statusCode == null) {
	    	out.write("<h3>Errore sconosciuto</h3>");
	    } else if(statusCode == null) {
	    	out.write("<h3>" + throwable.getMessage() + "</h3>");
	    } else if(statusCode != 500){
	    	out.write("<h3>Error - Dettagli</h3>");
	    	out.write("<strong>Stato</strong>:"+statusCode+"<br>");
	    	out.write("<strong>URI richiesta</strong>:"+requestUri);
	    } else {
	    	out.write("<h3>Eccezione - Details</h3>");
	    	out.write("<ul><li>Servlet:"+servletName+"</li>");
	    	out.write("<li>Exception:"+throwable.getClass().getName()+"</li>");
	    	out.write("<li>URI richiesta:"+requestUri+"</li>");
	    	out.write("<li>Messaggio dell'eccezione:"+throwable.getMessage()+"</li>");
	    	out.write("</ul>");
	    }
	    
	    out.write("<br><br>");
	    out.write("<a href=\"index.jsp\">Home Page</a>");
	    out.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
