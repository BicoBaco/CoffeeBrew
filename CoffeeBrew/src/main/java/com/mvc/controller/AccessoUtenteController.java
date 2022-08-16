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

			/* meglio farlo qua passando una struttura da UtenteDAO? la struttura non può essere il resultset
			 * non si può passare il resultset senza tenere la connessione al db aperta
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
			response.getWriter().println(accessoUtente.getNome() + " " +accessoUtente.getCognome());
			
			
			//sessione salvata
			
			//address = "index.jsp" ?
			//RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			//dispatcher.forward(request, response);
		}
	}

}
