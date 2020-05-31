
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

/**
 * Download files from S3 bucket and RDS
 */

@WebServlet("/Download")
public class Download extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";
	public static int BUFFER_SIZE = 1024 * 100;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topic = request.getParameter("Topic");
		String filename = request.getParameter("file_name");
		String username = request.getParameter("user_name");
		System.out.println("entered");
		System.out.println(filename);

		AWSCredentials credentials = new BasicAWSCredentials(Constants.ACCESS_KEY_ID, Constants.ACCESS_SEC_KEY);

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();

		String bucketName = Constants.BUCKET_NAME;

		try {

			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, filename));

			byte[] buffer = new byte[5 * 1024 * 1024];
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			ServletOutputStream output = response.getOutputStream();
			InputStream input = s3object.getObjectContent();

			try {

				int bytesRead = -1;
				while ((bytesRead = input.read(buffer)) != -1) {

					output.write(buffer, 0, bytesRead);
				}

			} finally {

				if (input != null) {
					input.close();
				}

				output.flush();
				if (output != null) {
					output.close();
				}
			}

			RequestDispatcher req = request.getRequestDispatcher("Search.jsp");
			req.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
