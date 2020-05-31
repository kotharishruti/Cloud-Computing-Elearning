
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	 *  Navigation Servlet which redirects to different servlets based on buttons on Home screen
	 */


@WebServlet("/Home")
public class Home extends HttpServlet {

   
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String button = request.getParameter("button");
        
        if ("Search".equals(button)) {
        	
        	request.getRequestDispatcher("Search.jsp").forward(request, response);
        } else if ("Upload".equals(button)) {
        	request.getRequestDispatcher("Upload.jsp").forward(request, response);
        
        }else if ("Delete".equals(button)) {
            request.getRequestDispatcher("GetUserFiles").forward(request, response);
       

        }
        else {
                request.getRequestDispatcher("/GetUserFiles").forward(request, response);

        	}

        }
        
    }
