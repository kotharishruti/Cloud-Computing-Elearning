<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">

th, td {
  padding: 10px;
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
.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: 330px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<div align="center" style="margin-top: 5%;">
		<h1 style="color:black;"> Login</h1>
		<h3 style="color:black;"> Please enter the credentials to login</h3>
	</div>
	<div align="center">
		<form action="Login" method="post">
			<table style="with: 50%">
 
				<tr>
					<td style="color:black;">Email ID</td>
					<td><input type="email" name="username" /></td>
				</tr>
				<tr>
					<td style="color:black;">Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			
				<tr>
					<td> 
					<input type="submit" value="Login" class = "button" />
					</td>
					
					<td> <p style="color:black;"> Don't have an Account? <a href = "Register_1.jsp" style="color:black;"><b>Create now</b></a> </p>
				</tr>
				
			</table>
		</form>
	</div>	
					<img src="images/bookHub.png" 	class="center">
</body>
</html>