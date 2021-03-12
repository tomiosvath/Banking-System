package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccessLayer.CustomerDAO;

/**
 * Servlet implementation class ViewBalance
 */
@WebServlet("/ViewBalance")
public class ViewBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBalance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO d = new CustomerDAO();	
		HttpSession s = request.getSession();
		String a = (String) s.getAttribute("username");
		int id = d.selectCustomerIdFromUsername(a);
		int bal = d.selectBalanceFromCustomerId(id);
		HttpSession session = request.getSession(true); 
		if(session!=null) {
			request.setAttribute("username", a); //a is username
			request.setAttribute("balance", "Your balance is: " + bal +".0 LEI");
			request.getRequestDispatcher("/Home1.jsp").forward(request, response);
			//System.out.println("Logged out");
		}
	}
}


