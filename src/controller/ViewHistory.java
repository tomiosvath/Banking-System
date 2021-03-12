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
 * Servlet implementation class ViewHistory
 */
@WebServlet("/ViewHistory")
public class ViewHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHistory() {
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
		String history = d.selectTransactionHistoryFromAccountId(id);
		HttpSession session = request.getSession(true); 
		if(session!=null) {
			request.setAttribute("username", a); //a is username
			String rep = "Your transaction history is:<br><br>" + history;
			request.setAttribute("history", rep);
			request.getRequestDispatcher("/Home1.jsp").forward(request, response);
		}
	}

	
}
