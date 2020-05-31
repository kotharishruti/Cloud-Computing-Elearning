
/**
	 * Database connections class
	 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	static Connection con = null;

	public static Connection RetriveConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(Constants.CONNECTION_STRING, Constants.USERNAME, Constants.PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
