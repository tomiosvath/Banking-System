package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccessLayer.AdminDAO;
import model.Admin;


/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 Admin c = new Admin();
		 c.setUsername(username);
		 c.setPassword(password);
		 
		 AdminDAO d= new AdminDAO(); 
		 String userValidate = d.authenticateUser(c);
		 if(userValidate.equals("success")) {
			 HttpSession session = request.getSession(true);
			 session.setAttribute("username", username);
			 request.setAttribute("username", username); 
			 request.getRequestDispatcher("/Admin.jsp").forward(request, response);
		 }
		 else {
			 request.setAttribute("errMessage", userValidate); 
			 request.getRequestDispatcher("/Logina.jsp").forward(request, response);
		 }
	}

}
