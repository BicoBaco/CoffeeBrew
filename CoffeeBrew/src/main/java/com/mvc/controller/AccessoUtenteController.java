package com.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mvc.bean.UtenteBean;
import com.mvc.dao.UtenteDAO;

/**
 * Servlet implementation class AccessoUtenteController
 */

public class AccessoUtenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoUtenteController() {
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
		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UtenteBean accessoUtente = new UtenteBean();
			accessoUtente.setEmail(email);
			accessoUtente.setPassword(password);
			
			boolean result;
			result = UtenteDAO.accediUtente(accessoUtente);

			/* meglio completare il bean qua passando una struttura da UtenteDAO? 
			 * la struttura non può essere il resultset, non si può passare senza tenere la connessione al db aperta
			try {
				if(result.next()) {
					accessoUtente.setNome(result.getString("nome"));
					accessoUtente.setCognome(result.getString("cognome"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			response.getWriter().println("Utente "+email+" dati corretti: "+result);
			if(result) {
				response.getWriter().println(accessoUtente.getNome() + " " +accessoUtente.getCognome() + " " 
												+ accessoUtente.getIdUtente() + " " + accessoUtente.getCentesimiCredito());
				//request.getSession(true).setAttribute("idUtente", accessoUtente.getIdUtente());
				request.getSession(true).setAttribute("utente", accessoUtente);
				
				
				/* login del prof con request.login ma vuole settate robe di contesto idk da capire
				response.getWriter().println("IsUserInRole?.."
	                        + request.isUserInRole("pr"));
				response.getWriter().println("getRemoteUser?.." + request.getRemoteUser());
				response.getWriter().println("getUserPrincipal?.."
	                        + request.getUserPrincipal());
				response.getWriter().println("getAuthType?.." + request.getAuthType());		
				
				try {
					request.login(email, password);
				} catch(ServletException ex) {
					response.getWriter().println("Login Failed with a ServletException.. " + ex.getMessage());
		                return;
		        }
				
				response.getWriter().println("after");
				response.getWriter().println("IsUserInRole?.."
                        + request.isUserInRole("pr"));
				response.getWriter().println("getRemoteUser?.." + request.getRemoteUser());
				response.getWriter().println("getUserPrincipal?.."
                        + request.getUserPrincipal());
				response.getWriter().println("getAuthType?.." + request.getAuthType());
				*/
			}
			
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
	}

}
