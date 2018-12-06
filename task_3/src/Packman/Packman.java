package Packman;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Packman 
{
	private Point3D gps;
	private double range;
	private double speed;
	private Image packman_image;

	
	public Packman(Point3D gps, double range, double speed) {
		super();
		this.gps = gps;
		this.range = range;
		this.speed = speed;
		File pathToFile = new File("resources/packman_image.png");
		try {
			Image packman_image = ImageIO.read(pathToFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Packman(Point3D gps, double speed) {
		this(gps,1,speed);
	}
	public Packman(Point3D gps) {
		this( gps ,1 ,1);
	}
	
	public Point3D getGps() {
		return gps;
	}
	public double getSpeed() {
		return speed;
	}
	public double getRange() {
		return range;
	}
	public void setRange(double range) {
		this.range = range;
	}
	public Image getPackman_image() {
		return packman_image;
	}
	public void setPackman_image(Image packman_image) {
		this.packman_image = packman_image;
	}
	public void setGps(Point3D gps) {
		this.gps = gps;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	

}
