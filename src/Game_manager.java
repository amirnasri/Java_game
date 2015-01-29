import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;


public class Game_manager {

	private Image[][] tile_map;
	private HashMap<Character, Image> game_images; 
	
	private void load_game_images() {
		
	}
	
	public static void main(String[] args) {
		
		BufferedReader f = new BufferedReader(new FileReader("tile_map"));
		String line;
		int row = 0;
		while ((line = f.readLine()) != null) {
			row += 1;
			for (int col = 0; col < line.length(); col++) {
				char tile = line.charAt(i);
				tile_map[row, col] = game_images.get(tile); 
			}
		}
	}
	
}
