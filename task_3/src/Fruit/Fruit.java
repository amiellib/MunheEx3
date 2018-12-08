package Fruit;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Fruit 
{
	private int fruit_id;
	private Point3D Gps;
	private double weight;
	public int getFruit_id() {
		return fruit_id;
	}

	public void setFruit_id(int fruit_id) {
		this.fruit_id = fruit_id;
	}

	public void setGps(Point3D gps) {
		Gps = gps;
	}

	private Image fruit_image;

	//	private boolean Eaten;
	/**
	 * build a Fruit 
	 * @param gps where is the Fruit placed
	 */
	public Fruit(int fruit_id ,Point3D gps , double weight)
	{
		this.fruit_id =fruit_id;
		this.Gps=new Point3D(gps);
		this.weight = weight;
		File pathToFile = new File("resources/fruit.png");
/*		try {
			Image fruit_image = ImageIO.read(pathToFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public Point3D getGps() {
		return Gps;
	}
	public Image getFruit_image() {
		return fruit_image;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
