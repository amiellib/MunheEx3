package Algorithems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import Coords.MyCoords;
import Geom.Point3D;
import Map.Map;
import entities.Fruit;
import entities.Game;
import entities.Packman;
import entities.Path;

public class Algorithems {
	
	MyCoords cord=new MyCoords();

	double ORIGIN_LON , ORIGIN_LAT , CORNER_LON , CORNER_LAT;
	final Point3D ORIGIN;
	private double TOTAL_DISTANCE_X ,TOTAL_DISTANCE_Y ,TOTAL_DISTANCE_ANGEL_LON ,TOTAL_DISTANCE_ANGEL_LAT;
	Random randomNum = new Random();

	
	public Algorithems(Map map)
	{
		super();
		ORIGIN_LON = map.getLeft_bottom_corner().y();
		CORNER_LAT = map.getLeft_bottom_corner().x();
		CORNER_LON = map.getRight_top_corner().y();
		ORIGIN_LAT = map.getRight_top_corner().x();
		ORIGIN = new Point3D(ORIGIN_LAT, ORIGIN_LON , 0 );
		TOTAL_DISTANCE_X = cord.distance3d(ORIGIN, new Point3D(ORIGIN_LAT , CORNER_LON ,0));
		TOTAL_DISTANCE_Y = cord.distance3d(ORIGIN, new Point3D(CORNER_LAT , ORIGIN_LON , 0));
		TOTAL_DISTANCE_ANGEL_LON = CORNER_LON - ORIGIN_LON;
		TOTAL_DISTANCE_ANGEL_LAT = CORNER_LAT - ORIGIN_LAT;
	}
	public Point3D convert_pixel_to_gps(Point3D pixel , int height, int  width)
	{
		return new Point3D(ORIGIN_LAT + (pixel.y()/height)*(TOTAL_DISTANCE_ANGEL_LAT),ORIGIN_LON+(pixel.x()/width)*(TOTAL_DISTANCE_ANGEL_LON) , pixel.z());
	}
	public Point3D convert_gps_to_pixel(Point3D gps  , int height, int width )
	{
		return new Point3D(width*(gps.y() - ORIGIN_LON)/(TOTAL_DISTANCE_ANGEL_LON)  ,height*(gps.x() - ORIGIN_LAT)/(TOTAL_DISTANCE_ANGEL_LAT) , gps.z());
	}
	public Point3D convert_meters_to_gps(Point3D meters)
	{
		return new Point3D(ORIGIN_LAT + meters.y()/TOTAL_DISTANCE_Y*(TOTAL_DISTANCE_ANGEL_LAT) ,ORIGIN_LON+meters.x()/TOTAL_DISTANCE_X*(TOTAL_DISTANCE_ANGEL_LON) , meters.z());
	}
	public Point3D convert_gps_to_meters(Point3D gps)
	{
		return new Point3D(TOTAL_DISTANCE_X*(gps.y() - ORIGIN_LON)/(TOTAL_DISTANCE_ANGEL_LON) ,TOTAL_DISTANCE_Y*(gps.x() - ORIGIN_LAT)/(TOTAL_DISTANCE_ANGEL_LAT) , gps.z());
	}
	public Point3D edge_until_eat(Point3D start , Point3D end , double range)
	{
		if(cord.distance3d(start, end)<=range)
			return start;
		Point3D meters_start = convert_gps_to_meters(start);
		Point3D meters_end = convert_gps_to_meters(end);
		Point3D vect = new Point3D(meters_end.x() - meters_start.x() , meters_end.y() - meters_start.y() , meters_end.z() - meters_start.z());
		double t = (Math.sqrt(vect.x()*vect.x() + vect.y()*vect.y() + vect.z()*vect.z()) - range)/Math.sqrt(vect.x()*vect.x() + vect.y()*vect.y() + vect.z()*vect.z());
		return convert_meters_to_gps(new Point3D(meters_start.x()+vect.x()*t ,meters_start.y()+vect.y()*t , meters_start.z()+vect.z()*t));
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
			if (row[0].equals("P"))
			{
				csv_game.getPackman_list().add(new Packman(Integer.parseInt(row[1]) , new Point3D(Double.parseDouble(row[2]) , Double.parseDouble(row[3]) , Double.parseDouble(row[4])) , Double.parseDouble(row[5]) , Double.parseDouble(row[6])));
			}
			else if (row[0].equals("F"))
			{
				csv_game.getFruit_list().add(new Fruit(Integer.parseInt(row[1]) , new Point3D(Double.parseDouble(row[2]) , Double.parseDouble(row[3]) , Double.parseDouble(row[4])) , Double.parseDouble(row[5])));
			}	
			line = br.readLine();
		}
		return csv_game;
	}
	public void create_csv_from_game(Game game , String path_file_name) throws IOException
	{
		FileWriter fileWriter = new FileWriter(path_file_name);
		fileWriter.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius\n");
		for (Packman packman : game.getPackman_list())
		{
			fileWriter.append("P," + packman.getPackman_id() +"," +packman.getGps().x()  + "," + packman.getGps().y() + "," + packman.getGps().z() + "," + packman.getSpeed() +","  +packman.getRange() +"\n");
		}
		for (Fruit fruit : game.getFruit_list())
		{
			fileWriter.append("F," + fruit.getFruit_id() +"," +fruit.getGps().x()  + "," + fruit.getGps().y() + "," + fruit.getGps().z() + "," + fruit.getWeight() +"\n");
		}
		fileWriter.flush();
		fileWriter.close();
	}

	public double get_max_path_time(Path [] paths)
	{
		double max =0;
		for(Path path : paths)
			max = (max<path.get_total_time()) ? path.get_total_time() : max;
			return max;

	}

	public int get_max_path(Path [] paths)
	{
		int max_path =0;
		double max_value=paths[0].get_total_time();
		for (int k=1;k<paths.length;k++)
		{
			if(paths[k].get_total_time()>max_value)
			{
				max_path = k;
				max_value = paths[k].get_total_time();
			}
		}
		return max_path;
	}
	public Path[] TSP(Game game)
	{
		Path[] paths_greedy_free = new Path [game.getPackman_list().size()];
		int counter =0;
		for (Packman this_packman : game.getPackman_list())
		{
			paths_greedy_free[counter] = new Path(this_packman);
			counter++;
		}
		Game temp_game = game.copy();
		double[] time_gone = new double [game.getPackman_list().size()];
		for(int i=0;i<game.getPackman_list().size(); i++)
			time_gone[i]=0;
		for (int i =0;i<game.getFruit_list().size();i++)
		{
			int[] array_min = get_matrix_min(temp_game).array_min;
			double[][] matrix = get_matrix_min(temp_game).matrix;
			double time_min = time_gone[0] +matrix[0][array_min[0]];
			int packman_to_put = 0;
			for(int j=1;j<game.getPackman_list().size();j++)
			{
				if (time_gone[j] + matrix[j][array_min[j]]<time_min)
				{
					packman_to_put = j;
					time_min = time_gone[j] + matrix[j][array_min[j]];
				}
			}			
			time_gone[packman_to_put] += matrix[packman_to_put][array_min[packman_to_put]];
			Point3D fruit_edge = edge_until_eat(paths_greedy_free[packman_to_put].getLocations().get(paths_greedy_free[packman_to_put].getLocations().size()-1) , temp_game.getFruit_list().get(array_min[packman_to_put]).getGps() ,game.getPackman_list().get(packman_to_put).getRange() );			
			paths_greedy_free[packman_to_put].getLocations().add(fruit_edge);
			temp_game.getPackman_list().get(packman_to_put).setGps(fruit_edge);
			temp_game.getFruit_list().remove(array_min[packman_to_put]).getGps();
		}
		for (int i=0;i<game.getFruit_list().size();i++)
			paths_greedy_free = adjustments(paths_greedy_free);

		double max_greedy_free=0;
		for(Path path : paths_greedy_free)
		{
			System.out.println(path.get_total_time());
			if(max_greedy_free<path.get_total_time())
			{
				max_greedy_free = path.get_total_time();
			}
		}
		return paths_greedy_free;
	}


	public MatrixMin get_matrix_min(Game game)
	{

		double min_value;
		MatrixMin matrixmin = new MatrixMin(new double [game.getPackman_list().size()][game.getFruit_list().size()],new int [game.getPackman_list().size()]);
		for (int i = 0; i<game.getPackman_list().size();i++)
		{
			min_value =cord.distance3d(game.getPackman_list().get(i).getGps(), game.getFruit_list().get(0).getGps())/game.getPackman_list().get(i).getSpeed();
			matrixmin.matrix[i][0] =  cord.distance3d(game.getPackman_list().get(i).getGps(), game.getFruit_list().get(0).getGps())/game.getPackman_list().get(i).getSpeed();
			matrixmin.array_min[i]=0;		
			for (int j = 1; j<game.getFruit_list().size();j++)
			{
				matrixmin.matrix[i][j] = cord.distance3d(game.getPackman_list().get(i).getGps(), game.getFruit_list().get(j).getGps())/game.getPackman_list().get(i).getSpeed();
				if(min_value>matrixmin.matrix[i][j])
				{
					matrixmin.array_min[i] = j;
					min_value = matrixmin.matrix[i][j];
				}
			}
		}
		return matrixmin;
	}
	public Path[] adjustments(Path[] paths)
	{
		if (paths.length>1)
			paths = adjusments_move_fruits(paths , get_max_path(paths));
		else
			paths[0] = adjustments_swap(paths[0]);
		return paths;
	}

	public Path swap(Path path ,int first_location , int second_location)
	{
		Collections.swap(path.getLocations(), first_location, second_location);
		return path;
	}
	public Path adjustments_swap(Path path)
	{
		Path path1;
		for (int i =0 ; i<path.getLocations().size()*path.getLocations().size() ; i++)
		{
			path1 = path.copy();
			for(int j=0;j<(int) path.getLocations().size()/2;j++)
			{
				swap(path1 , 1 + randomNum.nextInt(path1.getLocations().size()-1) , 1 + randomNum.nextInt(path1.getLocations().size()-1));
				if (path1.get_total_time()<path.get_total_time())
				{
					path = path1.copy();
					break;
				}
			}
		}
		return path;
	}
	public Path[] adjusments_move_fruits(Path [] paths , int max_path)
	{
		Path path1 , path2;

		for (int i =0 ; i<paths.length*30 ; i++)
		{
			int secondpackman = randomNum.nextInt(paths.length-1);
			path1 = paths[max_path].copy();
			path2 = paths[secondpackman].copy();
			path2.getLocations().add(path1.getLocations().remove(1 + randomNum.nextInt(path1.getLocations().size()-1)));
			path2 = adjustments_swap(path2);
			if (path2.get_total_time()<path1.get_total_time() && paths[max_path].get_total_time()>path1.get_total_time())
			{
				paths[max_path] = path1.copy();
				paths[secondpackman] = path2.copy();
			}
		}
		return paths;
	}
	
	public Point3D get_location_by_time(Path path ,Double time)
	{
		double time_left = time;
		double temp_time;
		for (int i =0 ; i<path.getLocations().size()-1;i++)
		{
			temp_time = cord.distance3d(path.getLocations().get(i) ,path.getLocations().get(i+1))/path.getMy_packman().getSpeed();
			if (temp_time<time_left)
			{
				time_left = time_left - temp_time;
			}
			else
			{
				Point3D meters_start = convert_gps_to_meters(path.getLocations().get(i));
				Point3D meters_end = convert_gps_to_meters(path.getLocations().get(i+1));
				Point3D vect = new Point3D(meters_end.x() - meters_start.x() , meters_end.y() - meters_start.y() , meters_end.z() - meters_start.z());
				double t = time_left/temp_time;
				Point3D newvec = new Point3D(vect.x()*t , vect.y()*t ,vect.z()*t);
				Point3D final_point_meters = new Point3D(meters_start.x()+newvec.x() ,meters_start.y()+newvec.y() , meters_start.z()+newvec.z());
				return convert_meters_to_gps(final_point_meters);	
			}
		}
		return path.getLocations().get(path.getLocations().size()-1);
	}
}




