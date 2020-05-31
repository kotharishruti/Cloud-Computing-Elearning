import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
/**
 * Login functionality to log into website
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
 
 
	private static final long serialVersionUID = 1L;

	public Login() {
        super();
        
    }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Validate empty fields
		
		if(username.isEmpty() || password.isEmpty() )
		{
			getServletContext().setAttribute("username", username);
			RequestDispatcher req = request.getRequestDispatcher("Register_3.jsp");
			req.include(request, response);
			 out.println (
	                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	                  "<html> \n" +
	                    "<head> \n" +
	                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                        "charset=ISO-8859-1\"> \n" +
	                      "<title> Error!! </title> \n" +
	                    "</head> \n" +
	                    "<body>" +
	                    "<div align=\"center\" style=\"margin-top: 50px; margin-left:100px >" +
	                      "<style= \"font-size=\"150px\" color='red'\"" + "\">" +
	                        "<h2 style=\"color:red; margin-top:50px; margin-left:50px\"> Username and password cannot be empty!</h2>" + 
	                    "</font></body> \n" +
	                  "</html>" 
	                );      
		}
		else
		{
			// Decrypt password
			try{  
				MessageDigest md = MessageDigest.getInstance("MD5"); 
				byte[] messageDigest = md.digest(password.getBytes());
				
				BigInteger no = new BigInteger(1, messageDigest); 
				String hash_password = no.toString(16); 
		        while (hash_password.length() < 32) { 
		        	hash_password = "0" + hash_password; 
		        } 
				
				Connection con = DBConnection.RetriveConnection();
				PreparedStatement ps=con.prepareStatement(  
				"SELECT username,email_id,password FROM user_test WHERE email_id=? and password=?");  
				ps.setString(1,username);  
				ps.setString(2,hash_password);  
				      
				ResultSet rs=ps.executeQuery();  
				if(rs.next()) {
					
					session.setAttribute("username",rs.getString("username"));
					RequestDispatcher req = request.getRequestDispatcher("/Register_4.jsp");
					req.forward(request, response);
					con.close();
				}
				else {
					
					// Validate username and password
					
					RequestDispatcher req = request.getRequestDispatcher("Register_3.jsp");
					req.include(request, response);
					 out.println (
			                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
			                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
			                  "<html> \n" +
			                    "<head> \n" +
			                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
			                        "charset=ISO-8859-1\"> \n" +
			                      "<title> Error!! </title> \n" +
			                    "</head> \n" +
			                    "<body>" +
			                    "<div align=\"center\" style=\"margin-top: 50px; margin-left:100px >" +
			                      "<style= \"font-size=\"150px\" color='red'\"" + "\">" +
			                        "<h2 style=\"color:red; margin-top:50px; margin-left:50px\">Sorry, username and password did not match!</h2>" + 
			                    "</font></body> \n" +
			                  "</html>" 
			                );      
				}
				
			          
			}catch(Exception e)
			{
				System.out.println(e);
			}           
		    out.close();  
		    

		}
	}
 
}