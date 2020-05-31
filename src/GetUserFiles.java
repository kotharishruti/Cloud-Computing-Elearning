
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * View Books once Uploaded.
 */

@WebServlet("/GetUserFiles")
public class GetUserFiles extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private Connection jdbcConnection;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession session = request.getSession();

			String username = (String) session.getAttribute("username");

			PrintWriter out = response.getWriter();
			Connection jdbcConnection = DBConnection.RetriveConnection();

			List<Books> listBook = new ArrayList<>();

			String sql = "SELECT username, bookname, category FROM books where username= ?";

			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, username);

			System.out.println(statement);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String user_name = resultSet.getString("username");
				String bookname = resultSet.getString("bookname");
				String category = resultSet.getString("category");
				Books book = new Books(user_name, bookname, category);
				listBook.add(book);
			}

			resultSet.close();
			statement.close();

			request.setAttribute("books", listBook);
			request.getRequestDispatcher("Delete.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}