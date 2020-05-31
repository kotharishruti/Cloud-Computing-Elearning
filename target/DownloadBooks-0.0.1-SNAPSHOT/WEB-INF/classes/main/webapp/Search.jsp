<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
<title>Logged In</title>
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
	<div class= "special">
	<form action="Search" method = "get">
          	<h3 align="center">Select a Category</h3>
            <h3 align="center">
              <select name="Topic" size="1">
                <option value="Machine Learning" selected>Machine Learning</option>
                <option value="Cloud Computing">Cloud Computing</option>
                <option value="Zoology">Zoology</option>
                <option value="Chemistry">Chemistry</option>
                <option value="Biology">Biology</option>
                <option value="Sports">Sports</option>     
              </select>
              <br/>
            </h3>
            <h3 align="center"><strong></strong>
            <% String username = request.getParameter("user_name");%>
            <% String topic = request.getParameter("Topic");%>
              <input type="hidden" name="user_name" value= "<%= username %>">
              <input type="submit" class="submit" value="search">
          </h3>
	</form>
	</div>
	</td>
	</tr>
	</table>
	</div>
	<div>
	<table class="w3-table-all w3-hoverable">
	<tr>
	<th class= "w3-center">Books</th>
	<th class = "w3-center">Download Link</th>
	</tr>
    	<c:forEach items="${books}" var="book">
    	<tr>
    		<td class = "w3-center">
    			${book.bookname}
    		</td>
    		<td class = "w3-center">
    			<form action="Download" method="get">
    			<button type="submit" name="button" value= "download">Click Here to Download</button>
    			<input type="hidden" name="user_name" value= "${book.username}">
    			<input type="hidden" name="Topic" value= "${book.cateogery}">
    			<input type="hidden" name="file_name" value= "${book.bookname}">
         	</form>
    		<td>
    	</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>