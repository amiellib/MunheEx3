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
		JMenuBar menuBarstatic = new JMenuBar(); // Window menu bar
		ToolBar trap=new ToolBar();
		JMenu fileMenu = new JMenu("File"); // Create File menu
		JMenu game = new JMenu("game"); // Create Elements menu
		menuBarstatic.add(fileMenu); // Add the file menu
		menuBarstatic.add(game); // Add the element menu
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem run = new JMenuItem("run");
		JMenuItem imp = new JMenuItem("import");
		JMenuItem fruit = new JMenuItem("fruit");
		JMenuItem pacman = new JMenuItem("pacman");
		game.add(fruit);
		game.add(pacman);
		fileMenu.add(exit);
		fileMenu.add(run);
		fileMenu.add(imp);
		Map map = new Map("src/Ariel1.png");	
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize(1433,642);
		map.setVisible(true);
		map.setLocationRelativeTo(null);
		map.setJMenuBar(menuBarstatic); // Add the menu bar to the window


	}



}
