<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- %@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %-->
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
select {
width:360px;
height:45px;
padding:12px;
margin-top:8px;
line-height:1;
border-radius:5px;
background-color:#4CAF50;
color:white;
font-size:20px;
outline:none
}

.submit {
width:353px;
height:45px;
color:#fff;
margin-top:20px;
background-color:#1067a2;
font-size:20px;
font-weight:700
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Delete Books</title>
</head>
<body>
<ul>
  <li><a href="Register_4.jsp"><font face="verdana" size="3">Home</font></a></li>
  <li><a href="Contact.jsp"><font face="verdana" size="3">Contact</font></a></li>
  <li><a href="About.jsp"><font face="verdana" size="3">About</font></a></li>
  <li><a href="Register_3.jsp"><font face="verdana" size="3">Logout</font></a></li>
</ul>
<% String username = request.getParameter("user_name");
	String name = (String)session.getAttribute("username");%>
	<div style="margin-left: 2%; margin-top: 7%;">
	<h3 align="center"> <%out.print(name); %>...To delete a file click on the button next to it </h3>
	<table class="w3-table w3-hoverable w3-striped">
	<tr>
	<th class= "w3-center w3-border">Books</th>
	<th class = "w3-center w3-border" >Category</th>
	<th class = "w3-center w3-border" >Delete File</th>
	</tr>
    	<c:forEach items="${books}" var="book">
    	<tr>
    		<td class = "w3-center w3-border">
    			${book.bookname}
    		</td>
    		<td class = "w3-center w3-border">
    			${book.category}
    		</td>
    		<td class = "w3-center w3-border">
    			<form action="Delete" method="get">
    			<button type="submit" name="button" value= "Delete">Delete</button>
    			<input type="hidden" name="user_name" value= "${book.username}">
	    			<input type="hidden" name="Topic" value= "${book.category}">
	    			<input type="hidden" name="file_name" value= "${book.bookname}">
    			
         	</form>
    		<td>
    	</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>