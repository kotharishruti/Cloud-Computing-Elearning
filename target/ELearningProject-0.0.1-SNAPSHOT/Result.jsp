<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Status</title>
    </head>
    <style>
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
    </style>
  
    <body> 
        <div  align="center" id="result">
            <h1 style="color:blue;">${requestScope["message"]}</h1><br/><br/>

        
        <form action="GetUserFiles" method="get">
			
			<button type="submit" value="result" class = "button">Click here to View All Files</button></form>
        </div>
    </body>
</html>