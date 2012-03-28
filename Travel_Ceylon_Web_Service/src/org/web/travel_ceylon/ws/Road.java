package org.web.travel_ceylon.ws;

/**
 * This Class Holds the Data of a Road form one city to another.
 * The data in a object of this class will be used to calculate the Trip Plan.
 * This is simply a edge of a graph. The graph is used to calculate the trip path.
 * @author ASLG
 *
 */
public class Road {

	public City from;
	public City to;
	public int distnce;
	
	public Road(City from,City to,int dis){
		this.from=from;
		this.to=to;
		distnce=dis;
	}
}
