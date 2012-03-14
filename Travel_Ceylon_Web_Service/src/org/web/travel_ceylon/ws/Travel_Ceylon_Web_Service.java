package org.web.travel_ceylon.ws;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;



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
	public String planTheTrip(String startC,String desC,String duration,String interests ) {
		Hashtable<String, City> city_table=new Hashtable<String,City>();
		ArrayList<City> cities = new ArrayList<City>();
		ArrayList<Road> roads = new ArrayList<Road>();
		int cityCount=0;
		
		String city_list[]=getCityList().split(";");
		for(String city : city_list){
				City t=new City(cityCount, city);
				cities.add(t);
				city_table.put(city, t);
				cityCount++;
		}

		connectToDB();
		try {
			stmt = (Statement) con.createStatement();
			String query = "SELECT * FROM roads ";
			System.out.println(query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				String from=resultSet.getString("from");
				String to=resultSet.getString("to");
				int dis=resultSet.getInt("distance");
				Road t=new Road(city_table.get(from),city_table.get(to),dis);
				roads.add(t);
				t=new Road(city_table.get(to),city_table.get(from),dis);
				roads.add(t);
				
			}
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed! :" + s);
		}


		Trip_Plan tp = new Trip_Plan();
		tp.calcShortestPaths(cities,roads);
		for (int k = 0; k < cities.size(); k++) {
			for (int i = 0; i < cities.size(); i++) {
				System.out.print(tp.DistanceArray[k][i] + ",");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		for (int k = 0; k < cities.size(); k++) {
			for (int i = 0; i < cities.size(); i++) {
				if (tp.Ancestor[k][i] != null) {
					System.out.print(tp.Ancestor[k][i].name + ",");
				} else {
					System.out.print(0 + ",");
				}
			}
			System.out.println();
		}
		ArrayList<City> path = tp.getShortestPath(cities.get(0), cities.get(6));
		for (int k = 0; k < path.size(); k++) {
			System.out.println(path.get(k).name);
		}
		
		System.out.println(startC+desC+duration+interests);
		return "Colombo:6.93408:79.8502:Gangaramya Temple|Buddhisum,History,Religon|A Big Temple in Colombo|6.91625|79.8563#Viharamahadevia Park|Lesuire|Park|6.91379|79.8626;Kalutara:6.58385:79.9611;Ambalangoda:6.2367:80.0544;Galle:6.03276:80.2157:Galle Fort|History|A fortress build by Dutches|6.02948|80.2161#Kottawa Jungle|Nature|A tropical rain forest|6.10147|80.3183;Weligama:5.97369:80.4294:Agrabhodi Raja Maha Viharaya|Religon - Buddhisum,History|A aention temple.|5.97132|80.4196;";
	}
}
