package Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Algorithems.Algorithems;
import Algorithems.Path;
import Fruit.Fruit;
import Game.Game;
import Geom.Point3D;
import Packman.Packman;
public class Map  extends JFrame 
{


	ArrayList<JLabel> label = new ArrayList<JLabel>(); 
	//    ImageIcon icon = new ImageIcon( new ImageIcon("src/fruit.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

	JPanel fruits_panel = new JPanel(); 
	private int color_counter = 0;
	private int fruit_counter = 0;
	Color[] colors = {Color.BLACK , Color.BLUE , Color.cyan , Color.GREEN , Color.GRAY , Color.MAGENTA ,Color.YELLOW ,Color.WHITE};
	String[] fruits = {"src/fruit.png" , "src/fruit2.png" ,"src/fruit3.png" ,"src/fruit4.png" ,"src/fruit5.png"};
	JPanel packmans_list = new JPanel();
	JPanel fruits_list = new JPanel();
	boolean is_packman = false;
	boolean is_fruit = false;
	boolean[] packman_fruit_null = {false,false,true};
	int fruit_id = 0;
	int packman_id =0;
	Game my_game = new Game();
	private BufferedImage backgroundImage;
	private BufferedImage fruit_image;
	private BufferedImage packman_image;
	private boolean run_program = false;
	private Path [] paths;

	Algorithems algo = new Algorithems();
	JMenuBar menuBarstatic;
	JMenu fileMenu , game_menu ,speed,csv;
	JMenuItem refresh , slowdown , fast_forwards , exit , run , save , fruit , packman , new_file , open;
	public Map(String fileName) throws IOException 
	{

		super("Pack Man Map");
		backgroundImage = ImageIO.read(new File(fileName));
		packman_image = ImageIO.read(new File("src/packman.png"));		


		JPanel bar = new JPanel();
		menuBarstatic = new JMenuBar(); // Window menu bar


		fileMenu = new JMenu("File"); // Create File menu
		game_menu = new JMenu("game"); // Create Elements menu
		speed = new JMenu("Speed"); // Create File menu
        csv=new JMenu("improt/export");



		menuBarstatic.add(fileMenu); // Add the file menu
		menuBarstatic.add(game_menu); // Add the element menu
		menuBarstatic.add(speed); // Add the element menu
		menuBarstatic.add(csv);


		refresh = new JMenuItem("refresh");
		slowdown = new JMenuItem("slow down");
		fast_forwards = new JMenuItem("fast forwards");

		exit = new JMenuItem("Exit");
		run = new JMenuItem("run");
		save = new JMenuItem("save");
		fruit = new JMenuItem("fruit");
		packman = new JMenuItem("pacman");
		new_file = new JMenuItem("new");
		open = new JMenuItem("open");

		speed.add(slowdown);
		speed.addSeparator();
		speed.add(fast_forwards);

		game_menu.add(fruit);
		game_menu.addSeparator();
		game_menu.add(packman);
		
		fileMenu.add(new_file);
        fileMenu.addSeparator();
		fileMenu.add(run);
		fileMenu.add(refresh);
        fileMenu.addSeparator();
		fileMenu.add(exit);
		
		csv.add(open);
		csv.addSeparator();
		csv.add(save);
		bar.add(menuBarstatic);



		Handler handler = new Handler();
		getContentPane().addMouseListener(handler);
		menuBarstatic.addMouseListener(handler);
		fruit.addActionListener(handler);
		packman.addActionListener(handler);
		refresh.addActionListener(handler);
		slowdown.addActionListener(handler);
		fast_forwards.addActionListener(handler);
		exit.addActionListener(handler);
		run.addActionListener(handler);
		save.addActionListener(handler);
		new_file.addActionListener(handler);
		open.addActionListener(handler);

	}
	public Color get_color()
	{
		color_counter ++;
		if (color_counter>=colors.length)
		{
			color_counter =0;
		}
		return colors[color_counter];
	}
	public BufferedImage get_fruit() throws IOException
	{
		fruit_counter ++;
		if (fruit_counter>=fruits.length)
		{
			fruit_counter =0;
		}
		fruit_image = ImageIO.read(new File(fruits[fruit_counter]));		
		return fruit_image;
	}
	public void paint(Graphics g) 
	{
		Image scaledImage = backgroundImage.getScaledInstance(this.getWidth(),this.getHeight(),backgroundImage.SCALE_SMOOTH);
		g.drawImage(scaledImage, 0, 0, null);
		setJMenuBar(menuBarstatic);
		for (Fruit fruit : my_game.getFruit_list())
		{
			try {
				g.drawImage(get_fruit(),(int) (algo.convert_gps_to_pixel(fruit.getGps(), getHeight(), getWidth()).x())-5, (int)(algo.convert_gps_to_pixel(fruit.getGps(), getHeight(), getWidth()).y())-5,30, 30, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		for(Packman packman :my_game.getPackman_list())
		{
			g.drawImage(packman_image,(int) (algo.convert_gps_to_pixel(packman.getGps(), getHeight(), getWidth()).x())-5, (int)(algo.convert_gps_to_pixel(packman.getGps(), getHeight(), getWidth()).y())-5,30, 30, null);
		}
		if (run_program)
		{
			for(Path path :paths)
			{
				g.setColor(get_color());
				for(int i=0;i<path.getLocations().size()-1;i++)
				{
					Point3D start = algo.convert_gps_to_pixel(path.getLocations().get(i), getHeight(), getWidth());
					Point3D end = algo.convert_gps_to_pixel(path.getLocations().get(i+1), getHeight(), getWidth());
					g.drawLine((int)start.x(), (int)start.y(), (int)end.x(),(int) end.y());
				}
			}
		}

	}


	public class Handler implements MouseListener , ActionListener , KeyListener{


		@Override
		public void mouseClicked(MouseEvent e) 
		{
			Point3D end = algo.convert_pixel_to_gps(new Point3D(e.getX(),e.getY(),0), getHeight(), getWidth());
			if (is_fruit)
			{
				my_game.getFruit_list().add(new Fruit(fruit_id, end , 1 ));
				fruit_id++;
				System.out.println("fruit ");
				
			}
			else if (is_packman)
			{
				my_game.getPackman_list().add(new Packman(packman_id, end , 1 ,1 ));
				packman_id++;
				System.out.println("packman ");

			}
			System.out.println(end);


			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {


		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//        System.out.println(e.getX() + "," + e.getY());

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//	        System.out.println(e.getX() + "," + e.getY());

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

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
			if(e.getSource()==refresh) 
			{
				my_game.getFruit_list().clear();
				my_game.getPackman_list().clear();
			}
			if(e.getSource()==slowdown) 
			{

			}
			if(e.getSource()==fast_forwards) 
			{

			}
			if(e.getSource()==exit) 
			{
				System.exit(0);
			}
			if(e.getSource()==run) 
			{
				paths = algo.TSP(my_game);
				repaint();
				run_program =true;

			}
			if(e.getSource()==save) 
			{
				try {
					JFrame parentFrame = new JFrame();
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to save");
					int userSelection = fileChooser.showSaveDialog(parentFrame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						algo.create_csv_from_game(my_game, fileToSave.toString());
						System.out.println("Save as file: " + fileToSave.getAbsolutePath());

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
			if(e.getSource()==new_file) 
			{
				my_game.getFruit_list().clear();
				my_game.getPackman_list().clear();
			}
			if(e.getSource()==open) {
				try {
					JFrame parentFrame = new JFrame();
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Specify a file to open");
					int userSelection = fileChooser.showOpenDialog(parentFrame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToLoad = fileChooser.getSelectedFile();
						my_game = algo.get_data_from_csv(fileToLoad.toString());
						System.out.println("opened file: " + fileToLoad.getAbsolutePath());
						repaint();

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	}
}

