<%@page import="java.util.List"%>
<%@page import="com.demichev.service.LoginService"%>
<%@page import="java.util.Date"%>
<%@page import="com.demichev.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <link rel="stylesheet" type="text/css" href="css/style.css"/>
	 <title>Result Page</title>	
</head>
<body>
<center>
	 <div id="container">
		 <h1>Result Page</h1>
			 <b>This is Sample Result Page</b><br/>
			 <%=new Date()%></br>
			 <%
				 Client user = (Client) session.getAttribute("client");
			 %>		
			 <b>Welcome <%= user.getLogin()%></b>		
			 <br/>
			 <a href="logout.jsp">Logout</a>
		 </p>

		 <table>
			 <thead>
				 <tr>
					 <th>User ID</th>
					 <th>Login</th>
					 <th>First Name</th>
					 <th>Middle Name</th>
					 <th>email</th>					
				 </tr>
			 </thead>
			 <tbody>
				 <%
					 LoginService loginService = new LoginService();
					 List<Client> list = loginService.getListOfUsers();
					 for (Client u : list) {
				 %>
				 <tr>
					 <td><%=u.getId()%></td>
					 <td><%=u.getLogin()%></td>
					 <td><%=u.getP().getName()%></td>
					 <td><%=u.getP().getSurname()%></td>
					 <td><%=u.getMail()%></td>
				 </tr>
				 <%}%>
			 <tbody>
		 </table>		
		 <br/>
	 </div>
	</center>	
</body>
</html>
