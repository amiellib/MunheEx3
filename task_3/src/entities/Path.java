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
	Algorithems algorithems = new Algorithems();
	private Packman my_packman;
	private double time = 0.0;
	private Color color;
	private ArrayList<Point3D> locations = new ArrayList<Point3D>();
	MyCoords cord = new MyCoords();
	Algorithems algo = new Algorithems();

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
	public double get_time_between_2_points(Point3D start , Point3D end)
	{
		return cord.distance3d(start, end)/my_packman.getSpeed();
	}
	public Point3D get_location_by_time(Double time)
	{
		double time_left = time;
		double temp_time;
		for (int i =0 ; i<locations.size()-1;i++)
		{
			temp_time = get_time_between_2_points(locations.get(i) ,locations.get(i+1));
			if (temp_time<time_left)
			{
				time_left = time_left - temp_time;
			}
			else
			{
				Point3D meters_start = algo.convert_gps_to_meters(locations.get(i));
				Point3D meters_end = algo.convert_gps_to_meters(locations.get(i+1));
				Point3D vect = new Point3D(meters_end.x() - meters_start.x() , meters_end.y() - meters_start.y() , meters_end.z() - meters_start.z());
				double t = time_left/temp_time;
				Point3D newvec = new Point3D(vect.x()*t , vect.y()*t ,vect.z()*t);
				Point3D final_point_meters = new Point3D(meters_start.x()+newvec.x() ,meters_start.y()+newvec.y() , meters_start.z()+newvec.z());
				return algo.convert_meters_to_gps(final_point_meters);	
			}
		}
		return locations.get(locations.size()-1);
		
	}
	
	
	@Override
	public String toString() {
		return "Path [algorithems=" + algorithems + ", my_packman=" + my_packman + ", time=" + time + ", locations="
				+ locations + "]";
	}
	

	
}
