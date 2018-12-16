package Map;
import java.io.IOException;
import javax.swing.JFrame;

import Geom.Point3D;

public class main
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		
		Map map = new Map(new Point3D (32.102010 , 35.202155 , 0) , new Point3D (32.106162 , 35.212514 , 0), "src/resources/Ariel1.png");	
		GUI_Map game = new GUI_Map(map);
	//	MyFrame map = new MyFrame();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(1233,642);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
	//	map.setJMenuBar(menuBarstatic); // Add the menu bar to the window


	}



}
