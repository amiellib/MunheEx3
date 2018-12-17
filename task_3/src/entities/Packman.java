package entities;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Packman 
{
	private int packman_id;
	private Point3D gps;
	private double range;
	private double speed;
	private Image packman_image;

	/**
	 * 
	 * @param packman_id packmans id
	 * @param gps packmans gps location
	 * @param speed packmans speed
	 * @param range packmans range
	 */
	public Packman(int packman_id ,Point3D gps, double speed , double range) {
		super();
		this.packman_id = packman_id;
		this.gps = gps;
		this.range = range;
		this.speed = speed;
		try {
			packman_image = ImageIO.read(new File("src/resources/packman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Packman(int packman_id , Point3D gps, double speed) {
		this(packman_id , gps,speed,1);
	}
	public Packman(int packman_id  , Point3D gps) {
		this(packman_id , gps ,1 ,1);
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
	public int getPackman_id() {
		return packman_id;
	}
	public void setPackman_id(int packman_id) {
		this.packman_id = packman_id;
	}
	@Override
	public String toString() {
		return "Packman [packman_id=" + packman_id + ", gps=" + gps + ", range=" + range + ", speed=" + speed
				+ ", packman_image=" + packman_image + "]";
	}
	
	

}
