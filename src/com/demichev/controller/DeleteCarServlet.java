package com.demichev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demichev.model.Car;
import com.demichev.model.Client;
import com.demichev.service.AddCarService;
import com.demichev.service.LoginService;

public class DeleteCarServlet  extends HttpServlet  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 993090133496518394L;
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		//setting html UTF-8
	 response.setContentType("text/html;charset=UTF-8");
	 //writer init
	 PrintWriter out = response.getWriter();

	 //getting car 
	 String carId = request.getParameter("carId");

		
	 try {	
		 //reg service init
		 AddCarService addCarService = new AddCarService();
		
		 //result is true when all parameters are ok and it is in database
		 boolean result = addCarService.delete(carId);	
		 
		 //showing html
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Deleting Car Successful</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 //show that all is ok if result is true
			 out.println("<h1>Thanks for Deleting Car :</h1>");
			 out.println("To get back please <a href=Main.jsp>Click here</a>");
		 }else{
			 //show that something was wrong during registration
			 out.println("<h1>Deleting a car Failed</h1>");
			 out.println("To try again<a href=Main.jsp>Click here</a>");
		 }
		 out.println("</center>");
		 out.println("</body>");
		 out.println("</html>");
	 } finally {	
		 //closing PrintWriter
		 out.close();
	 }
}

}
