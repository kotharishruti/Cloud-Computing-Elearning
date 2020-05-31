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
background-color:green;
color:white;
font-size:20px;
outline:none
}

.submit {
width:300px;
height:45px;
color:#fff;
margin-top:20px;
background-color:green;
font-size:15px;
font-weight:700
}
input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border-radius: 4px;
  box-sizing: border-box;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>File Upload</title>
</head>
<script>
function selectOnChange(listIndex){
	if(listIndex != null){
		//document.getElementById("selectedValue").value = listIndex;
		document.f1.selectedValue.value=listIndex;
		var collection = document.f2;
		for(var i=0; i<collection.length;i++){
			collection[i].selectedFile.value=listIndex;
		}
	}
}
function selectedValue(){  
    var value ="<%=request.getAttribute("selectedValue")%>";
    if(value !=null){
     	document.f1.Topic.selectedIndex=value ;
     	var collection = document.f2;
		for(var i=0; i<collection.length;i++){
			collection[i].selectedFile.value=value;
		}

    }    
} 
</script>
<body onload="selectedValue();">
<ul>
  <li><a href="Register_4.jsp"><font face="verdana" size="3">Home</font></a></li>
  <li><a href="Contact.jsp"><font face="verdana" size="3">Contact</font></a></li>
  <li><a href="About.jsp"><font face="verdana" size="3">About</font></a></li>
  <li><a href="Register_3.jsp"><font face="verdana" size="3">Logout</font></a></li>
</ul>
<% String name = (String)session.getAttribute("username");%>
<div align="left" style="margin-left: 2%; margin-top: 10%;">
<h3 align="center"> <%out.print(name); %>...Upload a book to help other readers </h3>
	<form  name = "f1"  action="Upload" method = "post" enctype="multipart/form-data">
	<table style="with: 50%" cellspacing="30">
	<tr>
	<td style="padding:0 50px 20px 10px;" class = "w3-center">
          	<h4 style="margin-left: 2%;margin-top: 25%;">Select the Category of your File</h4>
          	<br/>
            <h4 style="margin-top: %;">
              <select name="Topic" size="1"
              onchange="javascript: selectOnChange(this.selectedIndex)">
                <option value="Artificial_Intelligence" selected>Artificial Intelligence</option>
                <option value="Mathematics">Mathematics</option>
                <option value="Zoology">Zoology</option>
                <option value="Chemistry">Chemistry</option>
                <option value="Biology">Biology</option>
                <option value="Computer_Networks">Computer Networks</option>     
              </select>
              <input type="hidden" name="selectedValue" value="0"/> 
            </h4>
    </td>
	<td style="padding:0 50px 0 75px;" class = "w3-center ">
            
              <h4 style="margin-left: 5%;margin-top: 10%;">File to Upload </h4><input type="file" name="file_name" style="margin-left: 30%;"/>
              <input type="submit" class="submit" value="Upload" style="margin-left: 10%;">
              <input type="hidden" name="user_name" value= "<%= name %>">
              
    </td>
    </tr>
    </table>          
	</form>
	</div>
</body>
</html>