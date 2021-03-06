package org.web.travel_ceylon.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This class have the core functionality of generating trip plans. Uses Floyd
 * Warshall Algorithm to calculate trip path.
 * 
 * @author ASLG
 * 
 */
public class Trip_Plan {

	int[][] DistanceArray;
	City[][] Ancestor;

	ArrayList<City> cities;
	ArrayList<Road> roads;

	/**
	 * This will fill the matrix form the shortest paths.
	 * 
	 * @param cities
	 * @param roads
	 */
	public void calcShortestPaths(ArrayList<City> cities, ArrayList<Road> roads) {
		this.cities = cities;
		this.roads = roads;

		DistanceArray = initializeMap(cities, roads);
		// Printing For Checking//////////////////////////////////////
		for (int k = 0; k < cities.size(); k++) {
			for (int i = 0; i < cities.size(); i++) {
				System.out.print(DistanceArray[k][i] + ",");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
		// Printing For Checking//////////////////////////////////////

		Ancestor = new City[cities.size()][cities.size()];

		for (int k = 0; k < cities.size(); k++) {
			for (int i = 0; i < cities.size(); i++) {
				for (int j = 0; j < cities.size(); j++) {
					if (DistanceArray[i][k] != Integer.MAX_VALUE
							&& DistanceArray[k][j] != Integer.MAX_VALUE
							&& DistanceArray[i][k] + DistanceArray[k][j] < DistanceArray[i][j]) {
						DistanceArray[i][j] = DistanceArray[i][k]
								+ DistanceArray[k][j];
						Ancestor[i][j] = cities.get(k);
					}
				}
			}
		}
	}

	/**
	 * This will give you the shortest distance between the source and target.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public int getShortestDistance(City source, City target) {
		return DistanceArray[source.name][target.name];
	}

	/**
	 * This will give you the shortest path between the source and target.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public ArrayList<City> getShortestPath(City source, City target) {
		if (DistanceArray[source.name][target.name] == Integer.MAX_VALUE) {
			return new ArrayList<City>();
		}
		ArrayList<City> path = getIntermediatePath(source, target);
		path.add(0, source);
		path.add(target);
		return path;
	}

	/**
	 * This will give you the intermediate nodes of the shortest path between
	 * the source and target.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private ArrayList<City> getIntermediatePath(City source, City target) {
		if (DistanceArray == null) {
			throw new IllegalArgumentException(
					"Must call calcShortestPaths(...) before attempting to obtain a path.");
		}
		if (Ancestor[source.name][target.name] == null) {
			return new ArrayList<City>();
		}
		ArrayList<City> path = new ArrayList<City>();
		path.addAll(getIntermediatePath(source,
				Ancestor[source.name][target.name]));
		path.add(Ancestor[source.name][target.name]);
		path.addAll(getIntermediatePath(Ancestor[source.name][target.name],
				target));
		return path;
	}

	/**
	 * Initialize the two matrix on the details given by the parameters.
	 * 
	 * @param cities
	 * @param roads
	 * @return
	 */
	private int[][] initializeMap(ArrayList<City> cities, ArrayList<Road> roads) {
		int[][] distances = new int[cities.size()][cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			Arrays.fill(distances[i], Integer.MAX_VALUE);
		}
		for (Road e : roads) {
			distances[e.from.name][e.to.name] = e.distnce;
		}
		return distances;
	}
}