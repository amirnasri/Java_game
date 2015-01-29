import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game_manager extends JFrame {

	private Image[][] tile_map;
	private HashMap<Character, Image> game_images;
	private JPanel panel;
	
	public Game_manager() {
		
		panel = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				g.drawString("Hello", 100, 100);
			}
		};
		add(panel);
	}
	
	
	private void load_game_images() {
		ImageIcon im = new ImageIcon("images/player1.png");
		
	}
	
	private void load_tile_map() throws IOException {
		int row = 0;
		String line;
		BufferedReader f = new BufferedReader(new FileReader("tile_map"));
		
		while ((line = f.readLine()) != null) {
			row += 1;
			for (int col = 0; col < line.length(); col++) {
				char tile = line.charAt(col);
				tile_map[row][col] = game_images.get(tile); 
			}
		}
		f.close();
	}

	
	public void start() {
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Game_manager gm = new Game_manager();
		gm.start();
		
	}	
}
