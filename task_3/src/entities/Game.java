package entities;

import java.util.ArrayList;

public class Game {

	ArrayList<Fruit> fruit_list = new ArrayList<Fruit>();
	ArrayList<Packman> packman_list = new ArrayList<Packman>();
	double speed_rate = 10;
	
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
	public Game copy()
	{
		Game temp_game = new Game();
		for (Packman this_packman : packman_list)
		{
			temp_game.packman_list.add(new Packman (this_packman.getPackman_id() ,this_packman.getGps() , this_packman.getSpeed() ,this_packman.getRange()) );
		}
		for (Fruit this_fruit : fruit_list)
		{
			temp_game.fruit_list.add(new Fruit (this_fruit.getFruit_id() , this_fruit.getGps() , this_fruit.getWeight()));
		}	
		return temp_game;
	}
	@Override
	public String toString() {
		return "Game [fruit_list=" + fruit_list + ", packman_list=" + packman_list + ", speed_rate=" + speed_rate + "]";
	}
	


	
}
