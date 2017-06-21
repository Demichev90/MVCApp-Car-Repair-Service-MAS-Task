<html>
<head>
<title>Registration Form</title>
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
<form action="RegisterServlet" method="POST">
 
<table align="center" cellpadding = "10">
<tr>
<td>Login</td>
<td><input type="text" name="firstName" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
<tr>
<td>First Name</td>
<td><input type="text" name="Name" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
<tr>
<td>Last Name</td>
<td><input type="text" name="lastName" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
 
<tr>
<td>Phone</td>
<td><input type="text" name="phone" maxlength="30"/>
(max 30 characters a-z and A-Z)
</td>
</tr>
 
<tr>
<td>Email</td>
<td><input type="text" name="email" maxlength="100" /></td>
</tr>

<tr>
<td>Gender (male/female)</td>
<td><input type="text" name="gender" maxlength="100" /></td>
</tr>
<tr>
<td>Password</td>
<td><input type="text" name="password" maxlength="100" /></td>
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