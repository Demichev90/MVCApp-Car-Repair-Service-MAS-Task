<%@page import="com.demichev.model.Client"%>
<html>
<head>
<title>Add Car</title>
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
<h3>Registration Form</h3>
<form action="AddCarServlet" method="POST">
<table align="center" cellpadding = "10">
<tr>
<%
				 Client user = (Client) session.getAttribute("client");
			 %>		
<td>Brand</td>
<td>
<select name="brand" size="1">
<option value="Audi">Audi</option>
<option value="BMW">BMW</option>
<option value="Mercedes">Mercedes</option>
<option value="Saab">Saab</option>
<option value="VW">VW</option>
</select>
</td>
</tr>
<tr>
<td>Model</td>
<td><input type="text" name="model" maxlength="30"/>
(max 30 characters)
</td>
</tr>
<tr>
<tr>
<td>Some Special name</td>
<td><input type="text" name="nameOfCar" maxlength="30"/>
(max 30 characters)
</td>
</tr>
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