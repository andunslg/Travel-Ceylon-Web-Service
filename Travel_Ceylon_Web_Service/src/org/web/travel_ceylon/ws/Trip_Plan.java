package org.web.travel_ceylon.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Trip_Plan {

   int[][] DistanceArray;
   City[][] Ancestor;
   
   ArrayList<City> cities;
   ArrayList<Road> roads;

   public void calcShortestPaths(ArrayList<City> cities, ArrayList<Road> roads) {
	   this.cities=cities;
	   this.roads=roads;
	   
       DistanceArray = initializeMap(cities, roads);
       Ancestor = new City[cities.size()][cities.size()];

       for(int k=0; k<cities.size(); k++){
           for(int i=0; i<cities.size(); i++){
               for(int j=0; j<cities.size(); j++){
                   if(DistanceArray[i][k] != Integer.MAX_VALUE && DistanceArray[k][j] != Integer.MAX_VALUE && DistanceArray[i][k]+DistanceArray[k][j] < DistanceArray[i][j]){
                       DistanceArray[i][j] = DistanceArray[i][k]+DistanceArray[k][j];
                       Ancestor[i][j] = cities.get(k);
                   }
               }
           }
       }
   }

   public int getShortestDistance(City source, City target){
       return DistanceArray[source.name][target.name];
   }

   public ArrayList<City> getShortestPath(City source, City target){
       if(DistanceArray[source.name][target.name] == Integer.MAX_VALUE){
           return new ArrayList<City>();
       }
       ArrayList<City> path = getIntermediatePath(source, target);
       path.add(0, source);
       path.add(target);
       return path;
   }
  

   private ArrayList<City> getIntermediatePath(City source, City target){
       if(DistanceArray == null){
           throw new IllegalArgumentException("Must call calcShortestPaths(...) before attempting to obtain a path.");
       }
       if(Ancestor[source.name][target.name] == null){
           return new ArrayList<City>();
       }
       ArrayList<City> path = new ArrayList<City>();
       path.addAll(getIntermediatePath(source, Ancestor[source.name][target.name]));
       path.add(Ancestor[source.name][target.name]);
       path.addAll(getIntermediatePath(Ancestor[source.name][target.name], target));
       return path;
   }
  

   private int[][] initializeMap(ArrayList<City> cities, ArrayList<Road> roads){
       int[][] distances = new int[cities.size()][cities.size()];
       for(int i=0; i<cities.size(); i++){
           Arrays.fill(distances[i], Integer.MAX_VALUE);
       }
       for(Road e : roads){
           distances[e.from.name][e.to.name] = e.distnce;
       }
       return distances;
   }
}