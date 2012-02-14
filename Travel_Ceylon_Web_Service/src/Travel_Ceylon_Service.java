import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Travel_Ceylon_Service {
	private String url;
	private Connection con;
	private Statement stmt;

	public void connectToDB() {
		url = "jdbc:mysql://localhost:3306/sms";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.println("Error - Unable to Connect to the Database" + e);
		}
	}
}
