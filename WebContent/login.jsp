<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Login Page</title>

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

<form method="post" action="LoginServlet">


<div id="center">
<h3>Login</h3>
<br />
<table align="center" cellpadding = "10">



<tr>
<td>Username</td>
<td><input type="text" name="userId" title="Username" maxlength="30"/>
</td>
</tr>
<tr>
<td>Password</td>
<td><input type="password" name="password" maxlength="30"/>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input style="margin-left:100px;" type="submit" value="Login" />
</td>
</tr>
<tr>
<td colspan="2" align="center">
<a href="register.jsp" ">Register Here</a>
</td>
</tr>
</table>


</div>



</form>

</body>
</html>
