package entities;

import java.awt.Color;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Algorithems.Algorithems;
import Coords.MyCoords;
import Geom.Point3D;

public class Path {

	Random randomNum = new Random();
	private Packman my_packman;
	private double time = 0.0;
	private Color color;
	private ArrayList<Point3D> locations = new ArrayList<Point3D>();
	MyCoords cord = new MyCoords();

	public Path(Packman my_packman) {
		super();
		this.my_packman = my_packman;
		color =  new Color(randomNum.nextFloat(), randomNum.nextFloat(), randomNum.nextFloat());
		locations.add(my_packman.getGps());		
	}
	public ArrayList<Point3D> getLocations() {
		return locations;
	}
	public void setLocations(ArrayList<Point3D> locations) {
		this.locations = locations;
	}
	
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public double get_total_time()
	{
		return get_total_distance()/my_packman.getSpeed();
	}
	public double get_total_distance()
	{
		double total_distance = 0.0;
		Iterator<Point3D> iter = locations.iterator();
		Point3D current_location = iter.next();
		while (iter.hasNext())
		{
			Point3D temp_location = iter.next();
			total_distance += cord.distance3d(current_location,temp_location);
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
	
	public Packman getMy_packman() {
		return my_packman;
	}
	public void setMy_packman(Packman my_packman) {
		this.my_packman = my_packman;
	}
	@Override
	public String toString() {
		return "Path [algorithems=" + ", my_packman=" + my_packman + ", time=" + time + ", locations="
				+ locations + "]";
	}
	

	
}
