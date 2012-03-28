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
	private String tripPath = "";

	/**
	 * This connectToDB() method initialize the connection to the SQL database
	 * which holds the City data.
	 */
	public void connectToDB() {
		url = "jdbc:mysql://localhost:3306/travel_ceylon";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(url, "root", "");
		} catch (Exception e) {
			System.out.println("Error - Unable to Connect to the Database" + e);

		}
	}

	/**
	 * This getCategories() method will return a string which contains all the
	 * important place categories considered in the Travel Ceylon. The returning
	 * string will have list of categories separate by a ";"
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return catList;
	}

	/**
	 * This getLongitude_City(String city) method will return a longitude of a
	 * city.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lngt;
	}

	/**
	 * This getLatitude_City(String city) method will return a latitude of a
	 * city.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return latt;
	}

	/**
	 * This getLongitude_Im_Place(String place) method will return a longitude
	 * of a important place.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lngt;
	}

	/**
	 * This getLatitude_Im_Place(String place) method will return a latitude of
	 * a important place.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return latt;
	}

	/**
	 * This getCategory_Im_Place(String place) method will return a category of
	 * a important place.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cat;
	}

	/**
	 * This getDescription_Im_Place(String place) method will return a
	 * description of a important place.
	 */
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return des;
	}

	/**
	 * This insertImportantPlace(String placeName, String longitude,String
	 * latitude, String des, String cat, String city, String dis) method is used
	 * to, insert a new important place to the Travel Ceylon Database. The
	 * method caller will specify the, Place Name Longitude Latitude Categories
	 * The nearest city A description This data will be sent to approval.
	 */
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
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed! :" + s);
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityList;
	}

	/**
	 * This method is the most important method of this class This is used to
	 * calculate Trip Plans Following parameters are used to send the trip
	 * details
	 * 
	 * @param startC
	 *            = Can't be ""
	 * @param desC
	 *            = Can't be ""
	 * @param duration
	 *            = Can't be ""
	 * @param interests
	 *            = Can't be "", The list of interests in the Trip, List is set
	 *            of interests separated by ";"
	 * @param shouldInclude
	 *            = Can be "", This is a set of cities have to be included in
	 *            the trip plan. List is set of cities separated by ";"
	 * @param shouldAvoid
	 *            = Can be "", This is a set of cities have to be avoided in the
	 *            trip plan. List is set of cities separated by ";"
	 * @param observing
	 *            = Can't be "", This is a set of cities which will be used to
	 *            observe places, other cities in the trip plan will be
	 *            considered as just passing cities. List is set of cities
	 *            separated by ";"
	 * @return
	 */
	public String planTheTrip(String startC, String desC, String duration,
			String interests, String shouldInclude, String shouldAvoid,
			String observing) {

		System.out.println(startC + desC + duration + interests + shouldInclude
				+ shouldAvoid);

		ArrayList<String> interestList = new ArrayList<String>();
		ArrayList<String> shouldIcludeCities = new ArrayList<String>();
		ArrayList<String> shouldAvoidCities = new ArrayList<String>();
		ArrayList<String> observingCities = new ArrayList<String>();

		for (String interest : interests.split(";")) {
			interestList.add(interest);
		}
		for (String city : shouldInclude.split(";")) {
			shouldIcludeCities.add(city);
		}
		for (String city : shouldAvoid.split(";")) {
			shouldAvoidCities.add(city);
		}
		for (String city : observing.split(";")) {
			observingCities.add(city);
		}

		Hashtable<String, City> city_table = new Hashtable<String, City>();
		ArrayList<City> cities = new ArrayList<City>();
		ArrayList<Road> roads = new ArrayList<Road>();
		int cityCount = 0;

		String city_list[] = getCityList().split(";");
		for (String city : city_list) {
			if (!shouldAvoidCities.contains(city)) {// Use to remove should
													// avoid cities.
				City t = new City(cityCount, city);
				cities.add(t);
				city_table.put(city, t);
				cityCount++;
			}
		}

		// This will build the graph of cities in the Travel Ceylon Database.
		connectToDB();
		try {
			stmt = (Statement) con.createStatement();
			String query = "SELECT * FROM roads ";
			System.out.println(query);
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				String from = resultSet.getString("from");
				String to = resultSet.getString("to");
				int dis = resultSet.getInt("distance");
				if (!shouldAvoidCities.contains(from)
						&& !shouldAvoidCities.contains(to)) {
					Road t = new Road(city_table.get(from), city_table.get(to),
							dis);
					roads.add(t);
					t = new Road(city_table.get(to), city_table.get(from), dis);
					roads.add(t);
				}

			}
		} catch (SQLException s) {
			System.out.println("SQL statement is not executed! :" + s);
			tripPath = "";
			return tripPath;
		}
		// ///////////////////////////////////////////////////////////////////

		// Trip Planner object is created ////////////////////////////////////
		Trip_Plan tp = new Trip_Plan();
		tp.calcShortestPaths(cities, roads);

		// Printing For Checking/////////////////////////////////////////////
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
		// ///////////////////////////////////////////////////////////////////

		// This if will run if there are no specific included cities.////////
		if (shouldInclude.equals("")) {
			ArrayList<City> path = tp.getShortestPath(city_table.get(startC),
					city_table.get(desC));
			ArrayList<Integer> finalPath = new ArrayList<Integer>();
			for (City ct : path) {
				if (!finalPath.contains(ct.name)) {
					finalPath.add(ct.name);
				}
			}
			tripPath = "";

			for (int k = 0; k < finalPath.size(); k++) {
				System.out.println(cities.get(finalPath.get(k)).cityName);
				String cName = cities.get(finalPath.get(k)).cityName;
				String lng = Float.toString(getLongitude_City(cName));
				String lat = Float.toString(getLatitude_City(cName));
				tripPath += cName + ":" + lat + ":" + lng + ":";

				connectToDB();
				String place = "";
				String cat = "";
				try {
					stmt = (Statement) con.createStatement();
					ResultSet rs = stmt
							.executeQuery("SELECT Place_Name FROM close_to WHERE City_Name='"
									+ cName + "'");
					while (rs.next()) {
						place = rs.getString("Place_Name");
						cat = getCategory_Im_Place(place);
						String arr[] = cat.split(",");
						for (String ct : arr) {
							if (interestList.contains(ct)) {
								tripPath += place + "|" + cat + "|"
										+ getDescription_Im_Place(place) + "|"
										+ getLatitude_Im_Place(place) + "|"
										+ getLongitude_Im_Place(place) + "#";
							}
						}

					}

				} catch (SQLException e) {
					System.out.println("Error - Unable to get places close to "
							+ cName + " :" + e);
					tripPath = "";
					return tripPath;
				}
				tripPath += ";";
			}
			System.out.println(tripPath);

		}
		// This if will run if there are specific included cities.////////////
		else {
			ArrayList<City> path = tp.getShortestPath(city_table.get(startC),
					city_table.get(shouldIcludeCities.get(0)));
			if (shouldIcludeCities.size() > 1) {
				for (int i = 0; i < shouldIcludeCities.size() - 1; i++) {
					path.addAll(tp.getShortestPath(
							city_table.get(shouldIcludeCities.get(i)),
							city_table.get(shouldIcludeCities.get(i + 1))));
				}
			}
			path.addAll(tp.getShortestPath(city_table.get(shouldIcludeCities
					.get(shouldIcludeCities.size() - 1)), city_table.get(desC)));

			ArrayList<Integer> finalPath = new ArrayList<Integer>();
			for (City ct : path) {
				if (!finalPath.contains(ct.name)) {
					finalPath.add(ct.name);
				}
			}
			tripPath = "";

			for (int k = 0; k < finalPath.size(); k++) {
				System.out.println(cities.get(finalPath.get(k)).cityName);
				String cName = cities.get(finalPath.get(k)).cityName;
				String lng = Float.toString(getLongitude_City(cName));
				String lat = Float.toString(getLatitude_City(cName));
				tripPath += cName + ":" + lat + ":" + lng + ":";

				// This if will run if there are no specific observing cities.////////
				if (!observing.equals("")) {

					if (observingCities.contains(cName)) {
						connectToDB();
						String place = "";
						String cat = "";
						try {
							stmt = (Statement) con.createStatement();
							ResultSet rs = stmt.executeQuery("SELECT Place_Name FROM close_to WHERE City_Name='"+ cName + "'");
							while (rs.next()) {
								place = rs.getString("Place_Name");
								cat = getCategory_Im_Place(place);
								String arr[] = cat.split(",");
								for (String ct : arr) {
									if (interestList.contains(ct)) {
										tripPath += place
												+ "|"
												+ cat
												+ "|"
												+ getDescription_Im_Place(place)
												+ "|"
												+ getLatitude_Im_Place(place)
												+ "|"
												+ getLongitude_Im_Place(place)
												+ "#";
									}
								}

							}

						} catch (SQLException e) {
							System.out.println("Error - Unable to get places close to "+ cName + " :" + e);
							tripPath = "";
							return tripPath;
						}
					}

				} else {
					connectToDB();
					String place = "";
					String cat = "";
					try {
						stmt = (Statement) con.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT Place_Name FROM close_to WHERE City_Name='"+ cName + "'");
						while (rs.next()) {
							place = rs.getString("Place_Name");
							cat = getCategory_Im_Place(place);
							String arr[] = cat.split(",");
							for (String ct : arr) {
								if (interestList.contains(ct)) {
									tripPath += place + "|" + cat + "|"
											+ getDescription_Im_Place(place)
											+ "|" + getLatitude_Im_Place(place)
											+ "|"
											+ getLongitude_Im_Place(place)
											+ "#";
								}
							}

						}

					} catch (SQLException e) {
						System.out.println("Error - Unable to get places close to "+ cName + " :" + e);
						tripPath = "";
						return tripPath;
					}
				}
				tripPath += ";";
			}
			
			System.out.println(tripPath);
		}

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Cant close the dtabse Connection.class" + e);
			e.printStackTrace();
		}
		System.out.println(startC + desC + duration + interests + shouldInclude
				+ shouldAvoid+observing);
		return tripPath;
	}
}
