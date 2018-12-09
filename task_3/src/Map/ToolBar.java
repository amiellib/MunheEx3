package Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ToolBar
{
	private JMenuBar toolBar;
	public ToolBar() {
		JMenuBar toolBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File"); // Create File menu
	JMenu game = new JMenu("game"); // Create Elements menu
	toolBar.add(fileMenu); // Add the file menu
	toolBar.add(game); // Add the element menu
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
	
	}
	public JMenuBar getToolBar()
	{
		return toolBar;
	}

}
