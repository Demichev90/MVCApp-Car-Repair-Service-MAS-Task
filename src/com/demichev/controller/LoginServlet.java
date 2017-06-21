package com.demichev.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demichev.model.Client;
import com.demichev.service.LoginService;



//Servlet for logging in (Client login)
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7510867201949316800L;

	//on post method
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		//getting client name (it is unique)
	 String userId2 = request.getParameter("userId");
	 //getting client password
	 String password = request.getParameter("password");
	 
	 //Initialisation of login servise
	 LoginService loginService = new LoginService();
	 //if all is ok (client is registred and pass is ok ) = true
	 boolean result = loginService.authenticateUser(userId2, password);
	 //looking for such client
	 Client user = loginService.getUserByUserId(userId2);
	 if(result == true){
		 //send to home page of client
		 request.getSession().setAttribute("client", user);
		 response.sendRedirect("Main.jsp");
	 }
	 else{
		 //show error if  result is false
		 response.sendRedirect("error.jsp");
	 }
}

}