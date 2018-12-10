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
	
	public double get_total_time()
	{
		return get_total_distance()/my_packman.getSpeed();
	}
	public double get_total_distance()
	{
		MyCoords mycoords = new MyCoords();
		double total_distance = 0.0;
		Iterator<Point3D> iter = locations.iterator();
		Point3D current_location = iter.next();
		while (iter.hasNext())
		{
			//Point3D temp_location = algorithems.edge_until_eat(current_location, iter.next(), my_packman.getRange());
			Point3D temp_location = iter.next();
			total_distance += mycoords.distance3d(current_location,temp_location);
			current_location = temp_location;
		}
		return total_distance;
	}
	public Path copy()
	{
		Path temp_path = new Path(my_packman);
		int a = 0;
		for (Point3D location : locations)
		{
			if (a!=0)
				temp_path.locations.add(new Point3D(location.x(),location.y(),location.z()));
			a=1;
		}
		return temp_path;
		
	}
	@Override
	public String toString() {
		return "Path [algorithems=" + algorithems + ", my_packman=" + my_packman + ", time=" + time + ", locations="
				+ locations + "]";
	}
	

	
}
