package Game;

import java.io.IOException;

import Algorithems.Algorithems;
import Fruit.Fruit;
import Geom.Point3D;
import Packman.Packman;

public class main {

	public static void main(String[] args) throws IOException
	{
		Algorithems algo = new Algorithems();
		Game game = new Game();
		game.fruit_list.add(new Fruit(0, new Point3D(32.10526801,	35.21085983,	0), 1));
		game.fruit_list.add(new Fruit(1, new Point3D(32.1001,	35.2183,	0), 1));
		game.fruit_list.add(new Fruit(2, new Point3D(32.10512326801,	35.2123085983,	0), 1));

		game.packman_list.add(new Packman(0, new Point3D(32.10526801,	35.21085983,	0)));
		game.packman_list.add(new Packman(1, new Point3D(32.1026801,	35.2105983,	0)));
		game.packman_list.add(new Packman(2, new Point3D(32.106801,	35.25983,	0)));
	//	algo.create_csv_from_game(game, "/Users/shilo/Desktop/c_302537394_206328122/game.csv");


		
	}

}
