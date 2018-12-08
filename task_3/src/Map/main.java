package Map;

import java.io.IOException;

import javax.swing.JFrame;


public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map map = new Map("/Users/shilo/Desktop/data/Ariel1.png");
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setSize(1433,642);
		map.setVisible(true);
	}

}
