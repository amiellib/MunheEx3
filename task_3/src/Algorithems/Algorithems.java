package Algorithems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;

import Coords.MyCoords;
import Geom.Point3D;
import entities.Fruit;
import entities.Game;
import entities.Packman;
import entities.Path;

public class Algorithems {
	MyCoords cord=new MyCoords();

	double ORIGIN_LON =35.202155;
	double ORIGIN_LAT = 32.106162;
	double CORNER_LON = 35.212514;
	double CORNER_LAT = 32.102010;
	final Point3D ORIGIN = new Point3D(ORIGIN_LAT, ORIGIN_LON , 0 );
	final double TOTAL_DISTANCE_X = cord.distance3d(ORIGIN, new Point3D(ORIGIN_LAT , CORNER_LON ,0));
	final double TOTAL_DISTANCE_Y = cord.distance3d(ORIGIN, new Point3D(CORNER_LAT , ORIGIN_LON , 0));
	final double TOTAL_DISTANCE_ANGEL_LON = CORNER_LON - ORIGIN_LON;
	final double TOTAL_DISTANCE_ANGEL_LAT = CORNER_LAT - ORIGIN_LAT;
	Random randomNum = new Random();

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
		double totalD =  Math.sqrt(vect.x()*vect.x() + vect.y()*vect.y() + vect.z()*vect.z());



		double t = (totalD - range)/totalD;
		Point3D newvec = new Point3D(vect.x()*t , vect.y()*t ,vect.z()*t);
		Point3D final_point_meters = new Point3D(meters_start.x()+newvec.x() ,meters_start.y()+newvec.y() , meters_start.z()+newvec.z());
		return convert_meters_to_gps(final_point_meters);

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
		{
			if(max<path.get_total_time())
			{
				max = path.get_total_time();
			}
		}
		return max;
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
				if (time_gone[j] +matrix[j][array_min[j]]<time_min)
				{
					packman_to_put = j;
				}
			}			
			time_gone[packman_to_put] += matrix[packman_to_put][array_min[packman_to_put]];
			Point3D fruit_edge = edge_until_eat(paths_greedy_free[packman_to_put].getLocations().get(paths_greedy_free[packman_to_put].getLocations().size()-1) , temp_game.getFruit_list().get(array_min[packman_to_put]).getGps() ,game.getPackman_list().get(packman_to_put).getRange() );			
			paths_greedy_free[packman_to_put].getLocations().add(fruit_edge);
			temp_game.getPackman_list().get(packman_to_put).setGps(fruit_edge);
			temp_game.getFruit_list().remove(array_min[packman_to_put]).getGps();
		}


		for (int i=0;i<50;i++)
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
		//greedy
		System.out.println("greedy");
		Path[] paths_greedy = new Path [game.getPackman_list().size()];
		counter =0;
		for (Packman this_packman : game.getPackman_list())
		{
			paths_greedy[counter] = new Path(this_packman);
			counter++;
		}
		temp_game = game.copy();

		for (int i =0;i<game.getFruit_list().size();i++)
		{
			Matrix_single_min matrixmin = get_matrix_min_single(temp_game);
			Point3D fruit_edge = edge_until_eat(paths_greedy[matrixmin.column].getLocations().get(paths_greedy[matrixmin.column].getLocations().size()-1) , temp_game.getFruit_list().get(matrixmin.row).getGps() ,game.getPackman_list().get(matrixmin.column).getRange() );
			paths_greedy[matrixmin.column].getLocations().add(fruit_edge);		
			temp_game.getPackman_list().get(matrixmin.column).setGps(fruit_edge);
			temp_game.getFruit_list().remove(matrixmin.row).getGps();
		}
		for (int i=0;i<50;i++)
			paths_greedy = adjustments(paths_greedy);
		double max_greedy=0;
		for(Path path : paths_greedy)
		{
			if(max_greedy<path.get_total_time())
			{
				max_greedy = path.get_total_time();
			}
			System.out.println(path.get_total_time());
		}			
		return (max_greedy>max_greedy_free) ?   paths_greedy_free : paths_greedy;
	}

	public MatrixMin get_matrix_min(Game game )
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
			paths[max_path] = adjustments_swap(paths[max_path]);
			paths = adjusments_move_fruits(paths , max_path);

		}
		else
		{
			paths[0] = adjustments_swap(paths[0]);
		}
		return paths;
	}

	public Matrix_single_min get_matrix_min_single(Game game)
	{
		double value;
		int i=0;
		int j=0;
		Matrix_single_min matrixmin = new Matrix_single_min(99999999,0,0);
		for (Packman this_packman : game.getPackman_list())
		{
			for (Fruit this_fruit : game.getFruit_list())
			{
				value = cord.distance3d(this_packman.getGps(), this_fruit.getGps())/this_packman.getSpeed();
				if (matrixmin.getMin_value()>value)
				{
					matrixmin.setColumn(i);
					matrixmin.setRow(j);
					matrixmin.setMin_value(value);
				}	
				j++;
			}
			i++;
			j=0;
		}
		return matrixmin;
	}

	public Path swap(Path path ,int first_location , int second_location)
	{
		Collections.swap(path.getLocations(), first_location, second_location);
		return path;

	}
	public Path adjustments_swap(Path path)
	{
		Path path1;
		for (int i =0 ; i<20 ; i++)
		{
			path1 = path.copy();
			for(int j=0;j<10;j++)
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

		for (int i =0 ; i<50 ; i++)
		{
			int firstpackman = max_path;
			int secondpackman = randomNum.nextInt(paths.length-1);
			path1 = paths[firstpackman].copy();
			path2 = paths[secondpackman].copy();
			path2.getLocations().add(path1.getLocations().remove(1 + randomNum.nextInt(path1.getLocations().size()-1)));
			path2 = adjustments_swap(path2);
			if (path2.get_total_time()<path1.get_total_time() && paths[firstpackman].get_total_time()>path1.get_total_time())
			{
				paths[firstpackman] = path1.copy();
				paths[secondpackman] = path2.copy();
			}

		}

		return paths;
	}
}




