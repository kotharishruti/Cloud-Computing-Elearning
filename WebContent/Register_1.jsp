<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">

th, td {
  padding: 10px;
}
.button {
  background-color: green;
  border: none;
  color: white;
  padding: 10px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  margin: 40px 2px;
  cursor: pointer;
  width: 100px;
}
input {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>
</head>
<body>
<div align="center">
<h1 style=" margin-left: 65px" >Register Form</h1>
<form action="Register" method="post">
			<table>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Email ID</td>
					<td><input type="email" name="username" /></td>
				</tr>
					<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			</table>
			<input type="submit" value="Sign Up" class = "button" style=" margin-left: 75px"></form>
			
			<h3 style=" margin-left: 80px"><span>Click </span><a href="Register_3.jsp"><font size="4">here</font></a> to go to login page</h3>
</div>
</body>
</html>