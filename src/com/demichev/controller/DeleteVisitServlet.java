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

public class DeleteVisitServlet  extends HttpServlet  implements Serializable{

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
	 String visitId = request.getParameter("visitId");

		
	 try {	
		 //reg service init
		 AddCarService addCarService = new AddCarService();
		
		 //result is true when all parameters are ok and it is in database
		 boolean result = addCarService.deleteVisit(visitId);	
		 
		 //showing html
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Visit Delete Succesefull</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 //show that all is ok if result is true
			 out.println("<h1>Thanks for deleting Visit :</h1>");
			 out.println("To get back <a href=Main.jsp>Click here</a>");
		 }else{
			 //show that something was wrong during deleting
			 out.println("<h1>Deteting Visit failed</h1>");
			 out.println("To try again<a href=addCar.jsp>Click here</a>");
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
