package Map;

import java.io.IOException;

import javax.swing.JFrame;


public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map map = new Map("/Users/shilo/Desktop/map_for_packman.png");
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize(1000, 600);
		map.setVisible(true);
	}

}
