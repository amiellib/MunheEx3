package Packman;

import Geom.Point3D;

public class Packman 
{
private Point3D Gps;
private Point3D Orientation;
private double Speed;

public Packman(Point3D gps,Point3D orientation)
{
	this.Gps=new Point3D(gps);
	this.Orientation=new Point3D(orientation);
	this.Speed=1;
}
public Packman(Point3D gps,Point3D orientation,double speed)
{
	this.Gps=new Point3D(gps);
	this.Orientation=new Point3D(orientation);
	this.Speed=speed;
}
public Point3D getGps() {
	return Gps;
}
public Point3D getOrientation() {
	return Orientation;
}
public double getSpeed() {
	return Speed;
}

}
