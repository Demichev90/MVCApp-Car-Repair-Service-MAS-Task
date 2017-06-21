package com.demichev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demichev.model.Person;
import com.demichev.model.Person.Gender;
import com.demichev.model.Visit;
import com.demichev.model.Visit.Timing;
import com.demichev.model.Branch;
import com.demichev.model.Car;
import com.demichev.model.Client;
import com.demichev.service.AddCarService;
import com.demichev.service.LoginService;
import com.demichev.service.RegisterService;


//Servlet for CLient registration
public class ReserveServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4863000151264744329L;

	//Post method
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		 LoginService loginService = new LoginService();
		
		//setting html UTF-8
	 response.setContentType("text/html;charset=UTF-8");
	 //writer init
	 PrintWriter out = response.getWriter();
	
	 //getting date
	 String date = request.getParameter("date");
	 //getting time parameter
	 String time = request.getParameter("time");
	 //setting phone parameter as INTEGER
	 
	 //getting branch parameter
	 String branchStr = request.getParameter("branch");
	 Branch branch = (Branch) loginService.getBranchByName(branchStr);
	 //getting password
	 String carId = request.getParameter("car");
	 

	 DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	 Date datee = null;
	try {
		datee = format.parse(date);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 

	
	 
	 //Getting car with our ID
	 Car c = null;
	 for(Car carTemp :  loginService.getListOfAllCars()){
		 if (carTemp.getId() == Integer.parseInt(carId)) {
			c = carTemp;
		}
	 }
	 
	
	 
	 Client user =  (Client) request.getSession().getAttribute("client");
	 //Creating Visit
	 Visit v = new Visit(datee, c, branch, user);
	
	 //setting gender enum
	 if(time.equals("morning")){
		 v.setTiming(Timing.morning);
	 }else if(time.equals("lunch")){
		 v.setTiming(Timing.lunch);
	 }else if(time.equals("afternoon")){
		 v.setTiming(Timing.afternoon);
	 }else if(time.equals("evening")){
		 v.setTiming(Timing.evening);
	 }
	 
			
	 try {	
		 //reg service init
		 RegisterService registerService = new RegisterService();
		 //result is true when all parameters are ok and it is in database
		 boolean result = registerService.reserveVisit(v);	
		 
		 //showing html
		 out.println("<html>");
		 out.println("<head>");		
		 out.println("<title>Reservation Succesefull</title>");		
		 out.println("</head>");
		 out.println("<body>");
		 out.println("<center>");
		 if(result){
			 //show that all is ok if result is true
			 out.println("<h1>Thanks for Reservation :</h1>");
			 out.println("To get back<a href=Main.jsp>Click here</a>");
		 }else{
			 //show that something was wrong during registration
			 out.println("<h1>Reservation Failed</h1>");
			 out.println("To try again<a href=ReserveVisit.jsp>Click here</a>");
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