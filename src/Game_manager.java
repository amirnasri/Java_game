import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class Game_manager {

	HashMap<String, ImageIcon> images;
	
	public static void main(String[] args) throws IOException {
		Game_manager gm = new Game_manager();
		//gm.load_tile_map();
		gm.load_images();
		
		Screen_manager sc = new Screen_manager();
		Graphics2D g2d = sc.get_graphics();
		Image im = new ImageIcon("images/player1.png").getImage();
		g2d.drawImage(im, 100, 100, null); 
		g2d.dispose();
		sc.show();
	}

	Game_manager() {
		images = new HashMap<String, ImageIcon>();
	}

	void load_tile_map() throws IOException {
		BufferedReader map_file = new BufferedReader(new FileReader("maps/map1.txt"));
		String line;
		while ((line = map_file.readLine()) != null) {
			
			System.out.println(line);
			
		}
	}
	
	void load_images() {
	
		String image_path = "images/";
		images.put("o", new ImageIcon(image_path + "star1.png"));
		images.put("1", new ImageIcon(image_path + "grub1.png"));
		images.put("2", new ImageIcon(image_path + "fly1.png"));
		images.put("*", new ImageIcon(image_path + "heart1.png"));
		images.put("!", new ImageIcon(image_path + "music1.png"));
		
		for (char ch = 'A'; ch < 'H'; ch++) {
			images.put(String.valueOf(ch), new ImageIcon(image_path + "tile_" + ch));
		}
		
	}
}
