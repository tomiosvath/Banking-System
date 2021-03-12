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
 * Servlet implementation class AdminAddServlet
 */
@WebServlet("/AdminAddServlet")
public class AdminAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("fullname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String card_nr = request.getParameter("card_nr");
		String expire_date = request.getParameter("expire_date");
		String cvv = request.getParameter("cvv");
		String balance = request.getParameter("balance");
		
		CustomerDAO c = new CustomerDAO();
		c.insert(name, address, email, phone, username, password);
		
		int c_id = c.selectCustomerIdFromUsername(username);
		int cv = Integer.parseInt(cvv);
		int bal = Integer.parseInt(balance);
		String c_added = c.adminAdd(c_id, card_nr, expire_date, cv, bal);
		
		HttpSession s = request.getSession();
	    String a = (String) s.getAttribute("username");
	    
		if(c_added.equals("Customer created successfully!")) {
			request.setAttribute("username", a);
			request.setAttribute("add", c_added);
			request.getRequestDispatcher("/Admin.jsp").forward(request, response);
		}
		else {
			request.setAttribute("errMessage", c_added);
			request.getRequestDispatcher("/Registera.jsp").forward(request, response);
		}
	}

}
