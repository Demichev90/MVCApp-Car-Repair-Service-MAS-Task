<%@page import="com.demichev.model.Client"%>
<%@page import="com.demichev.model.Car"%>
<%@page import="com.demichev.model.Visit"%>
<%@page import="java.util.List"%>
<%@page import="com.demichev.service.LoginService"%>
<html>
<head>
<title>Main Page</title>
<style type="text/css">
body {
    background-image: url("https://pp.userapi.com/c837136/v837136367/55967/fjubJtfOm74.jpg");
   background-size: 100%;
}
#center {
	margin-top: 290px; 
}
h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:SlateBlue;
text-align: center; text-decoration: underline }
</style>

 <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
 
<body>

<div style="
    position: absolute;
    right: 60px;
    color: white;
    top: 10px;
">
<div class="wrapper">
  <span class="square individual">
    <a class="ninth before after" href="logout.jsp">Logout</a>
  </span>
</div>
 
</div>

<div id="center">
<%
				 Client user = (Client) session.getAttribute("client");
			 %>
<h3>Welcome <%= user.getLogin()%></h3>


<table align="center" cellpadding = "10">
<caption>Yours Cars</caption>
<caption><a href="addCar.jsp">Add New Car</a></caption>
			 <thead>
				 <tr>
				 <th>Brand</th>	
				<th>Model</th>
				<th>Name</th>
				<th></th>					 			
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 LoginService loginService = new LoginService();
					 List<Car> list = loginService.getListOfCars(user);
					 for (Car c : list) {
				 %>
				 <tr>
				 <td><%=c.getBrand()%></td>
					 <td><%=c.getModel()%></td>
					 <td><%=c.getName()%></td>
					 <td><form action="DeleteCarServlet" method="post">
  						 <button name="carId" value="<%=c.getId() %>">Delete</button>
					</form></td>
				 </tr>
				 <%}%>
				 
			 <tbody>
		 </table>	
		 
		 
		 <table align="center" cellpadding = "10">
<caption>Yours Visits</caption>
<caption><a href="ReserveVisit.jsp">Reserve Visit</a></caption>
			 <thead>
				 <tr>
				 <th>Car</th>	
				<th>Client</th>
				<th>Date</th>
				<th>Time</th>	
				<th>Branch</th>	
				<th></th>			 			
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 LoginService loginService2 = new LoginService();
					 List<Visit> list2 = loginService.getListOfVisits(user);
					 for (Visit v : list2) {
				 %>
				 <tr>
				 <td><%=v.getCar().getName()%></td>
					 <td><%=v.getClient().getP().getName()%> <%=v.getClient().getP().getSurname()%></td>
					 <td><%=v.getDate().toString()%></td>
					 <td><%=v.getTiming().toString()%></td>
					 <td><%=v.getBranch().getName()%></td>
					 <td><form action="DeleteVisitServlet" method="post">
  						 <button name="visitId" value="<%=v.getId() %>">Cancel Visit</button>
					</form></td>
				 </tr>
				 <%}%>
				 
			 <tbody>
		 </table>	
		
 </div>
</body>
</html>