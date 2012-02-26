package org.web.travel_ceylon.ws;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Travel_Ceylon_Web_Service {
	private String url;
	private Connection con;
	private Statement stmt;

	public void connectToDB() {
		url = "jdbc:mysql://localhost:3306/travel_ceylon";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.println("Error - Unable to Connect to the Database" + e);

		}
	}

	public String getCategories() {
		connectToDB();
		String catList = "";
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from category");

			while (rs.next()) {
				catList += rs.getString("Category") + ";";
			}
		} catch (SQLException e) {
			System.out.println("Error - Unable to get Categories :" + e);
		}
		return catList;
	}

	public float getLongitude_City(String city) {
		connectToDB();
		float lngt = 0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Longitude FROM city WHERE City_Name='"
							+ city + "'");
			rs.next();
			lngt = rs.getFloat("Longitude");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get longitude of " + city
					+ " :" + e);
		}
		return lngt;
	}

	public float getLatitude_City(String city) {
		connectToDB();
		float latt = 0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Latitude FROM city WHERE City_Name='"
							+ city + "'");
			rs.next();
			latt = rs.getFloat("Latitude");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get latitude of " + city
					+ " :" + e);
		}
		return latt;
	}

	public float getLongitude_Im_Place(String place) {
		connectToDB();
		float lngt = 0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Longitude FROM important_places WHERE Place_Name='"
							+ place + "'");
			rs.next();
			lngt = rs.getFloat("Longitude");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get longitude of " + place
					+ " :" + e);
		}
		return lngt;
	}

	public float getLatitude_Im_Place(String place) {
		connectToDB();
		float latt = 0;
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Latitude FROM important_places WHERE Place_Name='"
							+ place + "'");
			rs.next();
			latt = rs.getFloat("Latitude");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get latitude of " + place
					+ " :" + e);
		}
		return latt;
	}

	public String getCategory_Im_Place(String place) {
		connectToDB();
		String cat = "";
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Category FROM important_places WHERE Place_Name='"
							+ place + "'");
			rs.next();
			cat = rs.getString("Category");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get 	Category of " + place
					+ " :" + e);
		}
		return cat;
	}

	public String getDescription_Im_Place(String place) {
		connectToDB();
		String des = "";
		try {
			stmt = (Statement) con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT Description FROM important_places WHERE Place_Name='"
							+ place + "'");
			rs.next();
			des = rs.getString("Description");
		} catch (SQLException e) {
			System.out.println("Error - Unable to get Description of " + place
					+ " :" + e);
		}
		return des;
	}

	public boolean insertImportantPlace(String placeName, String longitude,
			String latitude, String des, String cat, String city, String dis) {
		connectToDB();
		try {
			stmt = (Statement) con.createStatement();
			String query = "INSERT important_places_for_approval VALUES('"
					+ placeName + "','" + cat + "','" + des + "','"
					+ Float.parseFloat(longitude) + "','"
					+ Float.parseFloat(latitude) + "')";
			System.out.println(query);
			int val = stmt.executeUpdate(query);
			query = "INSERT close_to_for_approval VALUES('" + placeName + "','"
					+ city + "','" + Float.parseFloat(dis) + "')";
			System.out.println(query);
			val = stmt.executeUpdate(query);
			return true;
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed! :" + s);
			return false;
		}

	}

	public String getCityList() {
		connectToDB();
		String cityList = "";
		try {
			stmt = (Statement) con.createStatement();
			String query = "SELECT * FROM city ";
			System.out.println(query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				cityList += resultSet.getString("City_Name") + ";";
			}
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed! :" + s);
		}
		return cityList;
	}
}
