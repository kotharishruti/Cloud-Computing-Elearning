<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
body {
    background-image:
        url('https://www.whylearn.com.sg/assets/images/cslider/e-learning%20wallpaper.png');
}

th, td {
  padding: 10px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<div align="center" style="margin-top: 15%; margin-left: 35%">
		<h1 style="color:white;"> Login</h1>
		<h3 style="color:white;"> Please enter the credentials to login</h3>
	</div>
	<div align="center" style="margin-left: 38%;">
		<form action="Login" method="post">
			<table style="with: 50%">
 
				<tr>
					<td style="color:white;">Email ID</td>
					<td><input type="email" name="username" /></td>
				</tr>
				<tr>
					<td style="color:white;">Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			
				<tr>
					<td> <input type="submit" value="Login" style=" height:100px; width:60px" /></td>
					<td> <p style="color:white;">Don't have an Account?  <a href = "Register_1.jsp" style="color:white;"><b>Create now</b></a> </p>
				</tr>	
			</table>
		</form>
	</div>	
</body>
</html>