package Algorithems;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Coords.MyCoords;
import Game.Game;
import Geom.Point3D;
import Packman.Packman;
import Fruit.Fruit;

public class Algorithems {
	MyCoords cord=new MyCoords();

	double ORIGIN_LON =35.202155;
	double ORIGIN_LAT = 32.106162;
	final Point3D ORIGIN = new Point3D(ORIGIN_LAT, ORIGIN_LON , 0 );
	final double TOTAL_DISTANCE_X = cord.distance3d(ORIGIN, new Point3D(ORIGIN_LAT , 35.212514 ,0));
	final double TOTAL_DISTANCE_Y = cord.distance3d(ORIGIN, new Point3D(32.102010 , ORIGIN_LON , 0));
	

	public Point3D convert_pixel_to_gps(Point3D pixel , int height , int width)
	{
		return new Point3D(ORIGIN_LAT - pixel.y()*TOTAL_DISTANCE_Y/height*0.000009060 ,ORIGIN_LON+pixel.x()*TOTAL_DISTANCE_X/width*  0.000012023 , pixel.z());
	}
	public Point3D convert_gps_to_pixel(Point3D gps , int height , int width)
	{
		return new Point3D(width/TOTAL_DISTANCE_X*(gps.y() - ORIGIN_LON) /000012023 ,height/TOTAL_DISTANCE_Y*(ORIGIN_LAT-gps.x())/0.000009060 , gps.z());
	}
	
	
	
	public Point3D convert_meters_to_gps(Point3D pixel)
	{
		return new Point3D(ORIGIN_LAT - pixel.y()*0.000009060 ,ORIGIN_LON+pixel.x()*0.000012023 , pixel.z());
	}
	public Point3D convert_gps_to_meters(Point3D gps)
	{
		return new Point3D((gps.y() - ORIGIN_LON) /000012023 ,(ORIGIN_LAT-gps.x())/0.000009060 , gps.z());
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
	
	public Game get_data_from_csv(String path_of_csv) throws IOException
	{
		Game csv_game = new Game();	
		BufferedReader br = new BufferedReader(new FileReader(path_of_csv));
		br.readLine();
		String line = br.readLine();
		while (line != null && !line.isEmpty()) {
			if((line.replaceAll(",","")).replaceAll(" ","").isEmpty())
				break;
			String[] row = line.split(",");
			if (row[0]=="P")
			{
				csv_game.getPackman_list().add(new Packman(Integer.parseInt(row[1]) , new Point3D(Double.parseDouble(row[2]) , Double.parseDouble(row[3]) , Double.parseDouble(row[4])) , Double.parseDouble(row[5]) , Double.parseDouble(row[6])));
			}
			else if (row[0]=="F")
			{
				csv_game.getFruit_list().add(new Fruit(Integer.parseInt(row[1]) , new Point3D(Double.parseDouble(row[2]) , Double.parseDouble(row[3]) , Double.parseDouble(row[4])) , Double.parseDouble(row[5])));
			}	
			line = br.readLine();
		}
		return csv_game;
	}
	
	public void TSP()
	{
		
	};
}
