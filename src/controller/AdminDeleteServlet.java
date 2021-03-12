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
 * Servlet implementation class AdminDeleteServlet
 */
@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
		
		 CustomerDAO d= new CustomerDAO(); 
		 String userValidate = d.del(username);
		 
		 HttpSession s = request.getSession();
		 String a = (String) s.getAttribute("username");
		 
		 if(userValidate.equals("User deleted successfuly!")) {
			
			 request.setAttribute("username", a);
			 request.setAttribute("del", userValidate); 
			 request.getRequestDispatcher("/Admin.jsp").forward(request, response);
		 }
		 else {
			 request.setAttribute("errMessage", userValidate); 
			 request.getRequestDispatcher("/DeleteUser.jsp").forward(request, response);
		 }
	}

}
