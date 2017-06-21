package com.demichev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.demichev.model.Car;
import com.demichev.model.Car.Brand;
import com.demichev.model.Client;
import com.demichev.service.AddCarService;


//Servlet for adding Car
public class AddCarServlet extends HttpServlet {
	
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

	 //getting brand parameter
	 String brand = request.getParameter("brand");

	 //getting model
	 String model = request.getParameter("model");
	 
	 //getting user login
	 String name = request.getParameter("nameOfCar");
	 
	Client user =  (Client) request.getSession().getAttribute("client");
	 
	 
	 //Creating Person
	 Car c = new Car(model,user, name);
	 //setting gender enum
	 if(brand.equals("Audi")){
		c.setBrand(Brand.Audi);
	 }else if(brand.equals("BMW")){
		 c.setBrand(Brand.BMW);
	 }else if(brand.equals("Saab")){
		 c.setBrand(Brand.Saab);
	 }else if(brand.equals("VW")){
		 c.setBrand(Brand.VW);
	 }else if(brand.equals("Mercedes")){
		 c.setBrand(Brand.Mercedes);
	 }
	 
			
	 try {	
		 //reg service init
		 AddCarService addCarService = new AddCarService();
		 //result is true when all parameters are ok and it is in database
		 boolean result = addCarService.register(c);	
		 
		 //showing html
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Adding Car Successful</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 //show that all is ok if result is true
			 out.println("<h1>Thanks for adding Car :</h1>");
			 out.println("Back to main <a href=Main.jsp>Click here</a>");
		 }else{
			 //show that something was wrong during registration
			 out.println("<h1>Adding a car Failed</h1>");
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