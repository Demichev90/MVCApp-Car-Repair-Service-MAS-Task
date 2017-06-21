<%@page import="com.demichev.model.Client"%>
<%@page import="com.demichev.model.Car"%>
<%@page import="java.util.List"%>
<%@page import="com.demichev.service.LoginService"%>
<html>
<head>
<title>Reserve Visit</title>
<style type="text/css">
body {
    background-image: url("https://pp.userapi.com/c837136/v837136367/55967/fjubJtfOm74.jpg");
   background-size: 100%;
}
#center {
	top: 50%; 
	left: 50%; 
	width: 450px; 
	height: 450px; 
	position: absolute; 
	margin-top: -225px; 
	margin-left: -225px; 
}
h3{font-family: Calibri; font-size: 22pt; font-style: normal; font-weight: bold; color:SlateBlue;
text-align: center; text-decoration: underline }
table{font-family: Calibri; color:black; font-size: 11pt; font-style: normal;width: 50%;
text-align:; background-color:white; border-collapse: collapse; border: 2px solid navy}
table.inner{border: 0px}
</style>
</head>
 
<body>

<div id="center">
<h3>Select for Visit</h3>
<form action="ReserveVisit" method="POST">
<table align="center" cellpadding = "10">
<tr>
<%
				 Client user = (Client) session.getAttribute("client");
			 
					 LoginService loginService = new LoginService();
					 List<Car> list = loginService.getListOfCars(user);
					 
				 %>	
<td>Select </td>
<td>
<select name="car" size="1">
<%for (Car c : list) {
String field = c.getName();
int carId = c.getId();%>
	<option value="<%=carId%>"><%out.println(c.getName()); %></option>				 					 
				<%	 } %>	
</select>
</td>
</tr>
<tr>
<td>Date</td>
<td><input name="date" id="date" type="date">
</td>
</tr>
<tr>
<td>Time</td>
<td>
<select name="time" size="1">
<option value="morning">Morning</option>
<option value="lunch">Lunch</option>
<option value="afternoon">Afternoon</option>
<option value="evening">Evening</option>
</select>
</td>
<tr>
<td>Branch</td>
<td>
<select name="branch" size="1">
<option value="chassis">Chassis</option>
<option value="engine">Engine</option>
<option value="body">Body</option>
<option value="diagnostics">Diagnostic</option>
</select>
</td>
<tr>
<td colspan="2" align="center">
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</td>
</tr>
</table>
</form>
 </div>
</body>
</html>