package Map;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import Algorithems.Algorithems;
import Geom.Point3D;
import entities.*;

public class GUI_Map  extends JFrame 
{

	private String[] packmans = {"src/resources/packman_eating_3.jpeg" , "src/resources/packman_eating2.png" ,"src/resources/packman_eating1.png" , "src/resources/packman_eating2.png" };
	private boolean is_packman = false , is_fruit = false , run_program = false;
	private int fruit_id = 0 , packman_id =0 , packman_counter =0 , global_time;
	private Game my_game = new Game();
	private BufferedImage backgroundImage , packman_image_eating_temp;
	private Path [] paths;
	private Algorithems algo; 
	private JMenuBar menuBarstatic;
	private JMenu fileMenu , game_menu ,speed,csv;
	private JMenuItem clean_map , slowdown , fast_forwards , exit , run , save , fruit , packman , new_file , open,kml,custom_fruit,custom_pacman;
	private JTextArea text_range,text_speed,text_gps,text_weight;
	private JLabel label;
	private JButton make_pacman_custom,make_fruit_custom;
	public GUI_Map(Map map) throws IOException 
	{
		super("Pack Man Map");
		this.algo = new Algorithems(map);
		backgroundImage = map.getBackgroundImage();

		menuBarstatic = new JMenuBar(); // Window menu bar
		text_gps = new JTextArea("please write gps here like this: x,y,z ", 10, 10); 
		text_weight=new JTextArea("please write the weight", 10, 10); 
		text_range=new JTextArea("please write the range", 10, 10); 
		text_speed=new JTextArea("please write the speed of pacman", 10, 10); 
	
        fileMenu = new JMenu("File"); // Create File menu
		game_menu = new JMenu("game"); // Create Elements menu
		speed = new JMenu("Speed"); // Create File menu
		csv=new JMenu("improt/export");
		label = new JLabel("nothing entered"); 
		menuBarstatic.add(fileMenu); // Add the file menu
		menuBarstatic.add(game_menu); // Add the element menu
		menuBarstatic.add(speed); // Add the element menu
		menuBarstatic.add(csv);

		//https://stackoverflow.com/questions/13366793/how-do-you-make-menu-item-jmenuitem-shortcut link for keyshorcut info
		clean_map = new JMenuItem("clean map");
		clean_map.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		slowdown = new JMenuItem("slow down");
		slowdown.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		fast_forwards = new JMenuItem("fast forwards");
		fast_forwards.setAccelerator(KeyStroke.getKeyStroke('U', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		run = new JMenuItem("run");
		run.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		save = new JMenuItem("save");
		save.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		fruit = new JMenuItem("fruit");
		fruit.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		packman = new JMenuItem("pacman");
		packman.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		new_file = new JMenuItem("new");
		new_file.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		open = new JMenuItem("open");
		open.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		kml = new JMenuItem("make kml");
		kml.setAccelerator(KeyStroke.getKeyStroke('K', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		custom_pacman = new JMenuItem("custom_pacman");
		custom_pacman.setAccelerator(KeyStroke.getKeyStroke('B', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		custom_fruit = new JMenuItem("custom_fruit");
		custom_fruit.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		speed.add(slowdown);
		speed.addSeparator();
		speed.add(fast_forwards);

		game_menu.add(fruit);
		game_menu.add(packman);
		game_menu.addSeparator();
		game_menu.add(custom_fruit);
		game_menu.add(custom_pacman);

		fileMenu.add(new_file);
		fileMenu.addSeparator();
		fileMenu.add(run);
		fileMenu.add(clean_map);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		csv.add(open);
		csv.addSeparator();
		csv.add(save);
		csv.addSeparator();
		csv.add(kml);
		setJMenuBar(menuBarstatic);

		Handler handler = new Handler();
		getContentPane().addMouseListener(handler);
		menuBarstatic.addMouseListener(handler);
		fruit.addActionListener(handler);
		packman.addActionListener(handler);
		clean_map.addActionListener(handler);
		slowdown.addActionListener(handler);
		fast_forwards.addActionListener(handler);
		exit.addActionListener(handler);
		run.addActionListener(handler);
		save.addActionListener(handler);
		new_file.addActionListener(handler);
		open.addActionListener(handler);
		kml.addActionListener(handler);
		custom_fruit.addActionListener(handler);
		custom_pacman.addActionListener(handler);
	}
	public BufferedImage get_packman() throws IOException
	{
		packman_counter = (++packman_counter>=packmans.length) ? 0 : packman_counter;
		return  ImageIO.read(new File(packmans[packman_counter]));
	}
	public void paint(Graphics g) 
	{
		g.drawImage(backgroundImage.getScaledInstance(this.getWidth(),this.getHeight(),backgroundImage.SCALE_SMOOTH), 0, 0, null);
		for (Fruit fruit : my_game.getFruit_list())
		{
			g.drawImage(fruit.getFruit_image(),(int) (algo.convert_gps_to_pixel(fruit.getGps(), getHeight(), getWidth()).x())-5, (int)(algo.convert_gps_to_pixel(fruit.getGps(), getHeight(), getWidth()).y())-5,30, 30, null);
		}
		for(Packman packman :my_game.getPackman_list())
		{
			g.drawImage(packman.getPackman_image(),(int) (algo.convert_gps_to_pixel(packman.getGps(), getHeight(), getWidth()).x())-5, (int)(algo.convert_gps_to_pixel(packman.getGps(), getHeight(), getWidth()).y())-5,30, 30, null);
		}
		if (run_program)
		{
			for(Path path :paths)
			{
				g.setColor(path.getColor());
				for(int i=0;i<path.getLocations().size()-1;i++)
				{
					Point3D start = algo.convert_gps_to_pixel(path.getLocations().get(i), getHeight(), getWidth());
					Point3D end = algo.convert_gps_to_pixel(path.getLocations().get(i+1), getHeight(), getWidth());
					g.drawLine((int)start.x(), (int)start.y(), (int)end.x(),(int) end.y());
				}
			}
			try {
				packman_image_eating_temp = get_packman();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			for(Path path :paths)
			{
				g.drawImage(packman_image_eating_temp,(int) (algo.convert_gps_to_pixel(algo.get_location_by_time(path, global_time*my_game.getSpeed_rate()), getHeight(), getWidth()).x())-5, (int)(algo.convert_gps_to_pixel(algo.get_location_by_time(path, global_time*my_game.getSpeed_rate()), getHeight(), getWidth()).y())-5,30, 30, null);
			}
		}
		menuBarstatic.repaint();
	}

	public class Handler implements MouseListener , ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			Point3D end = algo.convert_pixel_to_gps(new Point3D(e.getX()-10,e.getY()+30,0), getHeight(), getWidth());
			if (is_fruit)
			{
				my_game.getFruit_list().add(new Fruit(fruit_id, end , 1 ));
				fruit_id++;
			}
			else if (is_packman)
			{
				my_game.getPackman_list().add(new Packman(packman_id, end , 1 ,1 ));
				packman_id++;
			}
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(fruit)) {
				is_fruit = true;
				is_packman = false;
			}
			if(e.getSource()==packman) {
				is_fruit = false;
				is_packman = true;			
			}
			if(e.getSource()==clean_map) 
			{
				run_program = false;
				my_game.getFruit_list().clear();
				my_game.getPackman_list().clear();
				repaint();
			}
			if(e.getSource()==slowdown) 
			{
				my_game.setSpeed_rate(my_game.getSpeed_rate()/2);
			}
			if(e.getSource()==fast_forwards) 
			{
				my_game.setSpeed_rate(my_game.getSpeed_rate()*2);
			}
			if(e.getSource()==exit) 
			{
				System.exit(0);
			}
			if(e.getSource()==run) 
			{
				repaint();
				paths = algo.TSP(my_game);
				run_program =true;
				Thread thread = new Thread() 
				{
					@Override
					public void run() {thread_repainter();}
				};
				thread.start();
			}
			if(e.getSource()==save) 
			{
				try {
					JFrame parentFrame = new JFrame();
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");
					if (fileChooser.showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
						algo.create_csv_from_game(my_game, fileChooser.getSelectedFile().toString());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource()==new_file) 
			{
				run_program = false;
				my_game.getFruit_list().clear();
				my_game.getPackman_list().clear();
				repaint();
			}
			if(e.getSource()==open) 
			{
				run_program = false;
				try {
					JFrame parentFrame = new JFrame();
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to open");
					if (fileChooser.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
						my_game = algo.get_data_from_csv(fileChooser.getSelectedFile().toString());
						repaint();
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(e.getSource()==kml)
			{
				System.out.println(kml);
			}
			if(e.getSource()==custom_pacman)
			{
				menuBarstatic.add(text_gps);
				menuBarstatic.add(text_range);
				menuBarstatic.add(text_weight);
			}
			if(e.getSource()==custom_fruit)
			{
				menuBarstatic.add(text_gps);
				menuBarstatic.add(text_weight);
				menuBarstatic.add(make_fruit_custom);
			}
			if(e.getSource()==make_fruit_custom)
			{
			}
			if(e.getSource()==make_pacman_custom)
			{
				
			}
		}
	}
	public void thread_repainter()  
	{
		try
		{
			for (global_time=0;global_time*my_game.getSpeed_rate()<algo.get_max_path_time(paths);global_time++)
			{
				repaint();
				Thread.sleep(1000);
			}
			repaint();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public Algorithems getAlgo() {
		return algo;
	}	
}

