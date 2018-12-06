package Algorithems;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import Geom.Point3D;
import Packman.Packman;

public class Path {

	Algorithems algorithems = new Algorithems();
	Packman my_packman;
	double time = 0.0;
	ArrayList<Point3D> locations = new ArrayList<Point3D>();
	public Path(Packman my_packman) {
		super();
		this.my_packman = my_packman;
		locations.add(my_packman.getGps());		
	}
	public ArrayList<Point3D> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Point3D> locations) {
		this.locations = locations;
	}
	public double get_total_time(int height , int width)
	{
		return get_total_distance(height, width)/my_packman.getSpeed();
	}
	public double get_total_distance(int height , int width)
	{
		MyCoords mycoords = new MyCoords();
		double total_distance = 0.0;
		Iterator<Point3D> iter = locations.iterator();
		Point3D current_location = iter.next();
		while (iter.hasNext())
		{
			Point3D temp_location = algorithems.edge_until_eat(current_location, iter.next(), my_packman.getRange(), height, width);
			total_distance += mycoords.distance3d(current_location,temp_location);
			current_location = temp_location;
		}
		return total_distance;
	}
	

	
}
