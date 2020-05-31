<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
  position: fixed;
  top: 1%;
  width: 99%;
}

li {
  float: left;
  
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 20px 25px;
  text-decoration: none;
}

li a:hover {
  background-color: #111;
}
.active {
  background-color: #4CAF50;
}
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 50px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 30px;
  margin: 40px 2px;
  cursor: pointer;
  width: 400px;
}
.divider{
    width:20%;
    height:auto;
    display:inline-block;
}
form{
margin: 30px 230px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Profile</title>
</head>
<body>
<ul>
  <li><a href="Register_4.jsp"><font face="verdana" size="3">Home</font></a></li>
  <li><a href="#contact"><font face="verdana" size="3">Contact</font></a></li>
  <li><a href="#about"><font face="verdana" size="3">About</font></a></li>
  <li><a href="Register_3.jsp"><font face="verdana" size="3">Logout</font></a></li>
</ul>
<div align="left" style="margin-left: 23%; margin-top: 7%;">
	<table style="with: 50%">
	<tr>
		<td>
			<% String username = request.getParameter("user_name");%>
		</td>
	</tr>
	<tr></tr>
	<tr>
		<td> 
			<form action="Manage" method="get">
    			<button type="submit" name="button" value= "upload" class="button">Upload File</button>
    			<div class="divider"></div>
    			<button type="submit" name="button" value="delete" class="button">Delete Your Files</button>
    			<input type="hidden" name="user_name" value= "<%= username %>">
         	</form>
		</td>
	</tr>

</table>
</div>
</body>
</html>