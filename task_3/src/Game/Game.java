package Game;

import java.util.ArrayList;

import Fruit.Fruit;
import Packman.Packman;

public class Game {

	ArrayList<Fruit> fruit_list = new ArrayList<Fruit>();
	ArrayList<Packman> packman_list = new ArrayList<Packman>();
	double speed_rate;
	
	public ArrayList<Fruit> getFruit_list() {
		return fruit_list;
	}
	public ArrayList<Packman> getPackman_list() {
		return packman_list;
	}
	public double getSpeed_rate() {
		return speed_rate;
	}
	public void setFruit_list(ArrayList<Fruit> fruit_list) {
		this.fruit_list = fruit_list;
	}
	public void setPackman_list(ArrayList<Packman> packman_list) {
		this.packman_list = packman_list;
	}
	public void setSpeed_rate(double speed_rate) {
		this.speed_rate = speed_rate;
	}
	


	
}
