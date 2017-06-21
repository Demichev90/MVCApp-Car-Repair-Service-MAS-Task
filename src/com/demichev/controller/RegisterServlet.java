package com.demichev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demichev.model.Person;
import com.demichev.model.Person.Gender;
import com.demichev.model.Client;
import com.demichev.service.RegisterService;


//Servlet for CLient registration
public class RegisterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4863000151264744329L;

	//Post method
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		//setting html UTF-8
	 response.setContentType("text/html;charset=UTF-8");
	 //writer init
	 PrintWriter out = response.getWriter();
	 //getting parameter as login
	 String firstName = request.getParameter("firstName");
	 //getting name param
	 String fName = request.getParameter("Name");
	 //getting gender parameter
	 String gender = request.getParameter("gender");
	
	 //getting surname parameter
	 String lastName = request.getParameter("lastName");
	 //getting phone parameter
	 String phone2 = request.getParameter("phone");
	 //setting phone parameter as INTEGER
	 int phone = Integer.parseInt(phone2);
	 //getting mail parameter
	 String email = request.getParameter("email");
	 //getting password
	 String password = request.getParameter("password");
	 
	 
	 //Creating Person
	 Person p = new Person(fName, lastName, phone);
	 //creating new Client
	 Client user = new Client(p, email, password, firstName);
	 //setting gender enum
	 if(gender.equals("male")){
		 p.setGender(Gender.male);
	 }else{
		 p.setGender(Gender.female);
	 }
	 
			
	 try {	
		 //reg service init
		 RegisterService registerService = new RegisterService();
		 //result is true when all parameters are ok and it is in database
		 boolean result = registerService.register(user,p);	
		 
		 //showing html
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Registration Successful</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 //show that all is ok if result is true
			 out.println("<h1>Thanks for Registering with us :</h1>");
			 out.println("To login with new UserId and Password<a href=login.jsp>Click here</a>");
		 }else{
			 //show that something was wrong during registration
			 out.println("<h1>Registration Failed</h1>");
			 out.println("To try again<a href=register.jsp>Click here</a>");
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