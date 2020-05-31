<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 330px;
}
</style>
<meta charset="UTF-8">
<title>About US</title>
</head>
<body>
<ul>
  <li><a href="Register_4.jsp"><font face="verdana" size="3">Home</font></a></li>
  <li><a href="Contact.jsp"><font face="verdana" size="3">Contact</font></a></li>
  <li><a href="About.jsp"><font face="verdana" size="3">About</font></a></li>
  <li><a href="Register_3.jsp"><font face="verdana" size="3">Logout</font></a></li>
</ul>
<br/>
<div align="center" style="margin-left: 20px; margin-top: 10%;">
 <img src="images/bookHub.png" 	class="center">
  <br/><br/>
BookHub is a free service that helps millions of readers discover books they'll 
 love from 50 plus categories. <p>Upon joining, members can search and download a desired book. 
 A User can also help other readers by uploading e-books of his own.</p>
 BookHub was developed as a part of CS 218 course in San Jose State University, USA.
 <br/><br/>
 </div>
</body>
</html>