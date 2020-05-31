
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

@WebServlet("/Search")
public class Search extends HttpServlet {

	/**
	 * Search functionality to search all books that were uploaded to S3 based on
	 * Category
	 */
	private static final long serialVersionUID = 1L;
	private Connection jdbcConnection;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String selected_category = request.getParameter("Topic");
			String selectedValue = request.getParameter("selectedValue");
			if (selectedValue == null) {
				selectedValue = (String) request.getAttribute("selectedValue");
			}
			String username = request.getParameter("user_name");
			PrintWriter out = response.getWriter();
			out.println(selected_category);
			out.println(username);
			Connection jdbcConnection = DBConnection.RetriveConnection();
			List<Books> listBook = new ArrayList<>();
			String sql = "SELECT username, bookname FROM books where category= ?;";
			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, selected_category);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String user_name = resultSet.getString("username");
				String bookname = resultSet.getString("bookname");

				Books book = new Books(user_name, bookname, selected_category);
				listBook.add(book);
			}

			resultSet.close();
			statement.close();

			request.setAttribute("books", listBook);
			request.setAttribute("selectedValue", selectedValue);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}