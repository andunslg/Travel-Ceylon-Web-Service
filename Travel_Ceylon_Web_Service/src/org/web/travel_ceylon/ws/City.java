package org.web.travel_ceylon.ws;

/**
 * This Class Holds the Data of a City.
 * The data in a object of this class will be used to calculate the Trip Plan.
 * This is simply a node of a graph. The graph is used to calculate the trip path.
 * @author ASLG
 *
 */
public class City {
	public int name;
	public String cityName;
	
	public City(int name,String cName){
		this.name=name;
		this.cityName=cName;
	}
	
}
