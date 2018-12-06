package Map;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Coords.MyCoords;
import Geom.Point3D;

public class Map  extends JFrame  {
	MyCoords cord=new MyCoords();

	final Point3D ORIGIN = new Point3D(32.106162, 35.202155 , 0 );
	final double TOTAL_DISTANCE_X = cord.distance3d(ORIGIN, new Point3D(32.106162, 35.212514 ,0));
	final double TOTAL_DISTANCE_Y = cord.distance3d(ORIGIN, new Point3D(32.102010,35.202155 , 0));

	
	
	
	JPanel packman = new JPanel();
	JPanel fruit = new JPanel();
	boolean[] packman_fruit_null = {false,false,true};
	public Map(String fileName) throws IOException 
	{
		super("Pack Man Map");
		// https://stackoverflow.com/questions/1466240/how-to-set-an-image-as-a-background-for-frame-in-swing-gui-of-java
		final Image backgroundImage = javax.imageio.ImageIO.read(new File(fileName));	
//		final Image packmanImage = ImageIO.read(getClass().getResource("resources/packman.png"));
//		final Image fruitImage = javax.imageio.ImageIO.read(new File("/Users/shilo/Desktop/fruit.png"));	
	    setContentPane(new JPanel(new BorderLayout()) 
	    {
	        @Override 
	        public void paintComponent(Graphics g) 
	        {
	            g.drawImage(backgroundImage, 0, 0,getWidth(), getHeight(), this);
	        }
		});	
		Handler handler = new Handler();
		getContentPane().addMouseListener(handler);
//		JButton b=new JButton("Play", new ImageIcon("play.png"));    
//		b.setBounds(100,100,140, 40);   
	//	add(packman , BorderLayout.NORTH);
		
		
	}
	public class Handler implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent e) {
			Point3D end = new Point3D(32.106162 - e.getY()*TOTAL_DISTANCE_Y/getHeight()*0.000009060 ,35.202155+e.getX()*TOTAL_DISTANCE_X/getWidth()*  0.000012023,0);
			System.out.println(end);
			//System.out.println(cord.add(ORIGIN, new Point3D(e.getX()*TOTAL_DISTANCE_X/getWidth() , e.getY()*TOTAL_DISTANCE_Y/getHeight() , 0)));
//		    int x=e.getX();
//		    int y=e.getY();
//		    System.out.println(x+","+y);
//	        statusbar.setText(e.getX() + "," + e.getY());

		}

		@Override
		public void mousePressed(MouseEvent e) {
			
	//        System.out.println(e.getX() + "," + e.getY());
//	        statusbar.setText(e.getX() + "," + e.getY());
			
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

	}
}


