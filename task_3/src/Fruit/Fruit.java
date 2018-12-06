package Fruit;

import Geom.Point3D;

public class Fruit 
{
	private Point3D Gps;
	private boolean Eaten;
	/**
	 * build a Fruit 
	 * @param gps where is the Fruit placed
	 */
	public Fruit(Point3D gps)
	{
	this.Gps=new Point3D(gps);
	this.Eaten=false;
	}
	
	public boolean isEaten() {
		return Eaten;
	}

	public void setEaten(boolean eaten) {
		Eaten = eaten;
	}
	public Point3D getGps() {
		return Gps;
	}
}
