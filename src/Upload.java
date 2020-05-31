
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Upload functionality to upload books into S3 and RDS
 */

@MultipartConfig
@WebServlet("/Upload")
public class Upload extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selected_category = request.getParameter("Topic");
		String username = request.getParameter("user_name");
		Part filePart = request.getPart("file_name");
		System.out.println(filePart);
		String fileName = filePart.getSubmittedFileName();
		PrintWriter out = response.getWriter();
		Connection con;

		System.out.println(selected_category);
		System.out.println("fileName" + fileName);

		// Validate empty fields

		if (selected_category == null || fileName.isEmpty()) {

			out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +"
					+ "http://www.w3.org/TR/html4/loose.dtd\">\n" + "<html> \n" + "<head> \n"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; " + "charset=ISO-8859-1\"> \n"
					+ "<title> Error!! </title> \n" + "</head> \n" + "<body>"
					+ "<div align=\"center\" style=\"margin-top: 5%; margin-left: 35%>"
					+ "<style= \"font-size=\"400px\" color='red'\"" + "\">"
					+ "<h3 style=\"color:red;\" >Category and file is must!</h3>" + "</font></body> \n" + "</html>");
			RequestDispatcher req = request.getRequestDispatcher("Upload.jsp");
			req.include(request, response);

		} else {

			con = DBConnection.RetriveConnection();
			String sql = "SELECT bookname FROM books WHERE bookname=?";
			PreparedStatement statement;
			ResultSet resultSet;
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, fileName);
				resultSet = statement.executeQuery();
				if (resultSet.next() == false) {

					InputStream fileInputStream = filePart.getInputStream();

					String accessKeyId = Constants.ACCESS_KEY_ID;
					String secretAccessKey = Constants.ACCESS_SEC_KEY;

					String bucketName = Constants.BUCKET_NAME;

					BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretAccessKey);

					@SuppressWarnings("deprecation")
					AmazonS3 s3client = new AmazonS3Client(awsCreds);

					ObjectMetadata metadata = new ObjectMetadata();
					metadata.setContentLength(filePart.getSize());

					PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, fileName, fileInputStream,
							metadata);
					uploadRequest.setCannedAcl(CannedAccessControlList.PublicRead);

					try {
						System.out.println("Uploading file to s3");
						s3client.putObject(uploadRequest);

					} catch (AmazonServiceException ase) {
						System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
								+ "to Amazon S3, but was rejected with an error response" + " for some reason.");
						System.out.println("Error Message:    " + ase.getMessage());
						System.out.println("HTTP Status Code: " + ase.getStatusCode());
						System.out.println("AWS Error Code:   " + ase.getErrorCode());
						System.out.println("Error Type:       " + ase.getErrorType());
						System.out.println("Request ID:       " + ase.getRequestId());
					} catch (AmazonClientException ace) {
						System.out.println("Caught an AmazonClientException, which " + "means the client encountered "
								+ "an internal error while trying to " + "communicate with S3, "
								+ "such as not being able to access the network.");
						System.out.println("Error Message: " + ace.getMessage());

					} finally {

						System.out.println("File " + fileName + " uploaded successfully!! ");
						request.setAttribute("message", "File Uploaded Successfully");
						RequestDispatcher req = request.getRequestDispatcher("Result.jsp");
						req.forward(request, response);
					}

					sql = "INSERT INTO books VALUES(?, ?, ?)";
					statement = con.prepareStatement(sql);
					statement.setString(1, username);
					statement.setString(2, fileName);
					statement.setString(3, selected_category);
					statement.executeUpdate();

					System.out.println("Updated record in DB!!");

				} else {
					System.out.println("File " + fileName + " already exists!!");
					RequestDispatcher req = request.getRequestDispatcher("Upload.jsp");
					req.include(request, response);
					out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +"
							+ "http://www.w3.org/TR/html4/loose.dtd\">\n" + "<html> \n" + "<head> \n"
							+ "<meta http-equiv=\"Content-Type\" content=\"text/html; " + "charset=ISO-8859-1\"> \n"
							+ "<title> Error!! </title> \n" + "</head> \n" + "<body>"
							+ "<div align=\"center\" style=\"margin-top: 5%; margin-left: 35%>"
							+ "<style= \"font-size=\"400px\" color='red'\"" + "\">"
							+ "<h3 style=\"color:red;\" >File already exists!!</h3>" + "</font></body> \n" + "</html>");

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}