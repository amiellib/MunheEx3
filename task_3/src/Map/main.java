package Map;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class main
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		
		Map map = new Map("src/Ariel1.png");	
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize(1433,642);
		map.setVisible(true);
		map.setLocationRelativeTo(null);
	//	map.setJMenuBar(menuBarstatic); // Add the menu bar to the window


	}



}
