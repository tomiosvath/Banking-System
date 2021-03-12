package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccessLayer.AdminDAO;

/**
 * Servlet implementation class AdminReport
 */
@WebServlet("/AdminReport")
public class AdminReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO d = new AdminDAO();	
		HttpSession s = request.getSession();
		String a = (String) s.getAttribute("username");
		
		String history = d.selectTransactionHistory();
		HttpSession session = request.getSession(true); 
		if(session!=null) {
			request.setAttribute("username", a); //a is username
			String rep = "All the transactions that took place on this website are: <br><br>" + history;
			request.setAttribute("trans", rep);
			request.getRequestDispatcher("/Admin.jsp").forward(request, response);
		}
	}

	
}
