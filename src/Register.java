 
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

	/**
	 * Register functionality to register a user into RDS 
	 */

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/* User Registration */
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String email_id = request.getParameter("username");
		PrintWriter out = response.getWriter();
		
		if( username.isEmpty() || password.isEmpty() )
		{
			RequestDispatcher req = request.getRequestDispatcher("Register_1.jsp");
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
	                    "<div align=\"center\" style=\"margin-top: 50px; margin-left:90px >" +
	                      "<style= \"font-size=\"150px\" color='red'\"" + "\">" +
	                        "<h2 style=\"color:red; margin-top:60px; margin-left:80px\">Please fill all the fields</h2>" + 
	                    "</font></body> \n" +
	                  "</html>" 
	                );      
			
		}
		
		
		else
		{ // Encryption check 
			try {
				
				MessageDigest md = MessageDigest.getInstance("MD5"); 
				byte[] messageDigest = md.digest(password.getBytes());
				
				BigInteger no = new BigInteger(1, messageDigest); 
				String hash_password = no.toString(16); 
	            while (hash_password.length() < 32) { 
	            	hash_password = "0" + hash_password; 
	            } 
	            
	            // Uniqueness check to restrict one username and one email per person.
				
	            Connection con = DBConnection.RetriveConnection();
	            
	            String sql = "SELECT username FROM user_test WHERE email_id = ? or username = ? ;";
	            
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, email_id);
	            ps.setString(2, username);
	            ResultSet resultSet = ps.executeQuery();
				if(resultSet.next() == false) {	
					
					// If successful allows registration
	                      
		            sql = "insert into user_test values(?,?,?)";
		            ps = con.prepareStatement(sql);
		            ps.setString(1, username);
		            ps.setString(2, hash_password);
		            ps.setString(3, email_id);
		            ps.executeUpdate();
		            
		            RequestDispatcher req = request.getRequestDispatcher("Register_1.jsp");
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
			                    "<div align=\"center\">" +
			                      "<style= \"font-size=\"400px\" color='red'\"" + "\">" +
			                        "<h2 style=\"color:blue;margin-top:60px; margin-left:85px\" >User Successfully Registered!!</h2>" + 
			                    "</font></body> \n" +
			                  "</html>" 
			                );      
		           
	                
				}
				
				// Throws error to ensure username and email-id are unique for each user
				else {
					RequestDispatcher rd=request.getRequestDispatcher("Register_1.jsp");  
	     	        rd.include(request,response);  
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
	 	                        "<h2 style=\"color:red; margin-top:60px; margin-left:70px\">User already exists!</h2>" + 
	 	                    "</font></body> \n" +
	 	                  "</html>" 
	 	                );      
					
	                
				}
	        
	        }
	        catch(Exception se) {
	            se.printStackTrace();
	        }
			
		}
	}
 
}