package com.mvc.controller;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /***** Analyze The Servlet Exception *****/        
		
		//TODO sistemare

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        /***** Set Response Content Type *****/
        response.setContentType("text/html");

        /***** Print The Response *****/
        PrintWriter out = response.getWriter();
        String title = "Error/Exception Information";       
        String docType = "<!DOCTYPE html>\n";
        out.println(docType 
                + "<html>\n" + "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>" + title + "</title></head>\n" + "<body>");
        
        if (throwable == null && statusCode == null) {
            out.println("<h3>Error</h3>");           
        } else if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<ul><li><strong>Status Code</strong>?= "+ statusCode + "</li>");
            out.write("<li><strong>Requested URI</strong>?= "+ requestUri + "</li></ul>");
        } else {
            out.println("<h3>Exception Details</h3>");
            out.println("<ul><li><strong>Servlet Name</strong>?= " + servletName + "</li>");
            out.println("<li><strong>Exception Name</strong>?= " + throwable.getClass( ).getName( ) + "</li>");
            out.println("<li><strong>Requested URI</strong>?= " + requestUri + "</li>");
            out.println("<li><strong>Exception Message</strong>?= " + throwable.getMessage( ) + "</li></ul>");
        }

        out.println("<div> </div>Click <a id=\"homeUrl\" href=\"index.jsp\">home</a>");
        out.println("</body>\n</html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
