package Map;
import java.io.IOException;
import javax.swing.JFrame;

public class main
{
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		
		Map map = new Map("src/resources/Ariel1.png");	
	//	MyFrame map = new MyFrame();
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize(1233,642);
		map.setVisible(true);
		map.setLocationRelativeTo(null);
	//	map.setJMenuBar(menuBarstatic); // Add the menu bar to the window


	}



}
