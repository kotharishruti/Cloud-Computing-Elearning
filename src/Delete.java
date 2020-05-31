
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

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

/**
 * Delete files from S3 bucket and RDS
 */

@WebServlet("/Delete")
public class Delete extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection jdbcConnection;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String book_name = request.getParameter("file_name");

			String username = request.getParameter("user_name");

			PrintWriter out = response.getWriter();

			Connection jdbcConnection = DBConnection.RetriveConnection();

			// Delete from RDS books table

			String sql = "DELETE FROM books where bookname=?";

			PreparedStatement statement = jdbcConnection.prepareStatement(sql);
			statement.setString(1, book_name);
			int rowDeleted = statement.executeUpdate();
			statement.close();

			// Delete from S3 bucket

			AWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY_ID, Constants.ACCESS_SEC_KEY);

			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1)
					.build();

			String bucketName = Constants.BUCKET_NAME;

			s3client.deleteObject(new DeleteObjectRequest(bucketName, book_name));

			List<Books> listBook = new ArrayList<>();

			String sql1 = "SELECT username, bookname, category FROM books where username= ?;";

			PreparedStatement statement1 = jdbcConnection.prepareStatement(sql1);
			statement1.setString(1, username);

			ResultSet resultSet = statement1.executeQuery();

			while (resultSet.next()) {
				String user_name = resultSet.getString("username");
				String bookname = resultSet.getString("bookname");
				String category = resultSet.getString("category");

				Books book = new Books(user_name, bookname, category);
				listBook.add(book);
			}

			resultSet.close();
			statement1.close();

			request.setAttribute("books", listBook);
			request.getRequestDispatcher("Delete.jsp").forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
					+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		}

		catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
					+ "an internal error while trying to " + "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());

		}

		catch (Exception e) {

			e.printStackTrace();
		}
	}

}