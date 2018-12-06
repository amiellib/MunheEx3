package Algorithems;

import Coords.MyCoords;
import Geom.Point3D;

public class Algorithems {
	MyCoords cord=new MyCoords();

	double ORIGIN_LAT =35.202155;
	double ORIGIN_LON = 32.106162;
	final Point3D ORIGIN = new Point3D(ORIGIN_LON, ORIGIN_LAT , 0 );
	final double TOTAL_DISTANCE_X = cord.distance3d(ORIGIN, new Point3D(ORIGIN_LON , 35.212514 ,0));
	final double TOTAL_DISTANCE_Y = cord.distance3d(ORIGIN, new Point3D(32.102010 , ORIGIN_LAT , 0));
	

	public Point3D convert_pixel_to_gps(Point3D pixel , int height , int width)
	{
		return new Point3D(ORIGIN_LON - pixel.y()*TOTAL_DISTANCE_Y/height*0.000009060 ,ORIGIN_LAT+pixel.x()*TOTAL_DISTANCE_X/width*  0.000012023 , pixel.z());
	}
	public Point3D convert_gps_to_pixel(Point3D gps , int height , int width)
	{
		return new Point3D(width/TOTAL_DISTANCE_X*(gps.y() - ORIGIN_LAT) /000012023 ,height/TOTAL_DISTANCE_Y*(ORIGIN_LON-gps.x())/0.000009060 , gps.z());
	}
	
	
	
	public Point3D convert_meters_to_gps(Point3D pixel)
	{
		return new Point3D(ORIGIN_LON - pixel.y()*0.000009060 ,ORIGIN_LAT+pixel.x()*0.000012023 , pixel.z());
	}
	public Point3D convert_gps_to_meters(Point3D gps)
	{
		return new Point3D((gps.y() - ORIGIN_LAT) /000012023 ,(ORIGIN_LON-gps.x())/0.000009060 , gps.z());
	}
	public Point3D edge_until_eat(Point3D start , Point3D end , double range , int height , int width)
	{
		Point3D meters_start = convert_gps_to_meters(start);
		Point3D meters_end = convert_gps_to_meters(end);
		Point3D vect = new Point3D(meters_end.x() - meters_start.x() , meters_end.y() - meters_start.y() , meters_end.z() - meters_start.z());
		double totalD =  Math.sqrt(vect.x()*vect.x() + vect.y()*vect.y() + vect.z()*vect.x());

				
		
		double t = (totalD - range)/totalD;
		Point3D newvec = new Point3D(vect.x()*t , vect.y()*t ,vect.z()*t);
		double x_pixel_per_meter = width / TOTAL_DISTANCE_X;
		double y_pixel_per_meter = height / TOTAL_DISTANCE_Y;
		Point3D final_point = new Point3D(newvec.x()*x_pixel_per_meter , newvec.y() *y_pixel_per_meter , newvec.z());
		
		return new Point3D(convert_pixel_to_gps(final_point , height , width));
		
	}
	
	
	
	
	
	public void TSP()
	{
		
	};
}
