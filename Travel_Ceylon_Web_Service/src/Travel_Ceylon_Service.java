
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Travel_Ceylon_Service {
	private String url;
	private Connection con;
	private Statement stmt;

	public void connectToDB() {
		url = "jdbc:mysql://localhost:3306/travel_ceylon";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.println("Error - Unable to Connect to the Database" + e);

		}
	}

//	public ArrayList<String> getCategories() {
//		ArrayList<String> catList = new ArrayList<String>();
//		try {
//			stmt = con.createStatement();
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * from category");
//
//			while (rs.next()) {
//				catList.add(rs.getString("Category"));
//			}
//		} catch (SQLException e) {
//			System.out.println("Error - Unable to get Categories :" + e);
//		}
//		return catList;
//	}
	
	public float getLongitude_City(String city) {
		connectToDB();
		float lngt = 0;
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Longitude FROM city WHERE City_Name='"+city+"'");
			rs.next();
			lngt=rs.getFloat("Longitude");		
			} catch (SQLException e) {
			System.out.println("Error - Unable to get longitude of "+city+" :" + e);
		}
		return lngt;
	}
	public float getLatitude_City(String city) {
		connectToDB();
		float latt = 0;
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Latitude FROM city WHERE City_Name='"+city+"'");
			rs.next();
			latt=rs.getFloat("Latitude");	
			} catch (SQLException e) {
			System.out.println("Error - Unable to get latitude of "+city+" :" + e);
		}
		return latt;
	}
	public float getLongitude_Im_Place(String place) {
		connectToDB();
		float lngt = 0;
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Longitude FROM important_places WHERE Place_Name='"+place+"'");
			rs.next();
			lngt=rs.getFloat("Longitude");	
			} catch (SQLException e) {
			System.out.println("Error - Unable to get longitude of "+place+" :" + e);
		}
		return lngt;
	}
	public float getLatitude_Im_Place(String place) {
		connectToDB();
		float latt = 0;
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Latitude FROM important_places WHERE Place_Name='"+place+"'");
			rs.next();
			latt=rs.getFloat("Latitude");	
			} catch (SQLException e) {
			System.out.println("Error - Unable to get latitude of "+place+" :" + e);
		}
		return latt;
	}
	public String getCategory_Im_Place(String place) {
		connectToDB();
		String cat = "";
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Category FROM important_places WHERE Place_Name='"+place+"'");
			rs.next();
			cat=rs.getString("Category");	
			} catch (SQLException e) {
			System.out.println("Error - Unable to get 	Category of "+place+" :" + e);
		}
		return cat;
	}
	public String getDescription_Im_Place(String place) {
		connectToDB();
		String des = "";
		try {
			stmt = con.createStatement();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Description FROM important_places WHERE Place_Name='"+place+"'");
			rs.next();
			des=rs.getString("Description");	
			} catch (SQLException e) {
			System.out.println("Error - Unable to get Description of "+place+" :" + e);
		}
		return des;
	}
}
