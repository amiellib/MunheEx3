package Fruit;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Fruit 
{
	private Point3D Gps;

	private Image fruit_image;

	//	private boolean Eaten;
	/**
	 * build a Fruit 
	 * @param gps where is the Fruit placed
	 */
	public Fruit(Point3D gps)
	{
		this.Gps=new Point3D(gps);
		File pathToFile = new File("resources/fruit.png");
		try {
			Image fruit_image = ImageIO.read(pathToFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Point3D getGps() {
		return Gps;
	}
	public Image getFruit_image() {
		return fruit_image;
	}
}
