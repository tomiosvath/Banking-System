package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccessLayer.CustomerDAO;
import model.Account;
import model.Customer;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernamea = request.getParameter("your username");
		String usernameb = request.getParameter("receiver username");
		String amount = request.getParameter("amount");
		Customer a = new Customer();
		a.setUsername(usernamea);
		
		Customer b = new Customer();
		b.setUsername(usernameb);
		Account froma = new Account();
		Account fromb = new Account();
		CustomerDAO d = new CustomerDAO();
		int ida = d.selectCustomerIdFromUsername(usernamea);
		a.setCustomer_id(ida);
		int idb = d.selectCustomerIdFromUsername(usernameb);
		b.setCustomer_id(idb);
		
		int idacca = d.selectIdaccountFromCustomerId(ida);
		froma.setIdaccount(idacca);
		int idaccb = d.selectIdaccountFromCustomerId(idb);
		fromb.setIdaccount(idaccb);
		
		int balancea = d.selectBalanceFromCustomerId(ida);
		froma.setBalance(balancea);
		int balanceb = d.selectBalanceFromCustomerId(idb);
		fromb.setBalance(balanceb);
		
		int am = Integer.parseInt(amount);
		String userValidate = d.makeTransaction(a, b, froma, fromb, am);
		
		if(userValidate.equals("success")) {
			 //request.setAttribute("username", username); 
			 request.getRequestDispatcher("/Home1.jsp").forward(request, response);
		 }
		 else {
			 request.setAttribute("errMessage", userValidate); 
			 request.getRequestDispatcher("/Transfer.jsp").forward(request, response);
		 }
	}

}
