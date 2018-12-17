package entities;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Fruit 
{
	private int fruit_id;
	private Point3D Gps;
	private double weight;
	private Image fruit_image;
	String[] fruits = {"src/resources/fruit.png" , "src/resources/fruit2.png" ,"src/resources/fruit3.png" ,"src/resources/fruit4.png" ,"src/resources/fruit5.png"};
	Random randomNum = new Random();
	private boolean is_eaten = false;
	/**
	 * get friut id
	 * @return fruit id
	 */
	public int getFruit_id() {
		return fruit_id;
	}
	/**
	 * set fruit id
	 * 
	 */
	public void setFruit_id(int fruit_id) {
		this.fruit_id = fruit_id;
	}
	/**
	 * set fruit gps 
	 * 
	 */
	public void setGps(Point3D gps) {
		Gps = gps;
	}


	/**
	 * build a Fruit 
	 * @param gps where is the Fruit placed
	 */
	public Fruit(int fruit_id ,Point3D gps , double weight)
	{
		this.fruit_id =fruit_id;
		this.Gps=new Point3D(gps);
		this.weight = weight;
		try {
			fruit_image = ImageIO.read(new File(fruits[randomNum.nextInt(fruits.length-1)]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 *  get fruit Gps
 * @return Gps
 */
	public Point3D getGps() {
		return Gps;
	}
	/**
	 *  get fruit image
	 * @return fruit image
	 */
	public Image getFruit_image() {
		return fruit_image;
	}
	/**
	 *  get fruit weight
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * set fruit weight 
	 * 
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
/**
 * check if fruit is eaten
 * @return is_eaten
 */
	public boolean isIs_eaten() {
		return is_eaten;
	}
	/**
	 * set fruit is_eaten 
	 * 
	 */
	public void setIs_eaten(boolean is_eaten) {
		this.is_eaten = is_eaten;
	}

	@Override
	/**
	 * @return string of fruit
	 */
	public String toString() {
		return "Fruit [fruit_id=" + fruit_id + ", Gps=" + Gps + ", weight=" + weight + ", fruit_image=" + fruit_image
				+ "]";
	}
	
	
}
