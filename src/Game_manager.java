import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.Timer;
import javax.swing.ImageIcon;


public class Game_manager {

	private HashMap<Character, Image> images;
	private Image[][] tiles;
	private LinkedList<Sprite> sprites;
	private Sprite player;
	private Screen_manager screen_manager;
	private Input_manager input_manager;
	private int tile_width;
	private int tile_height;
	
	private final static int Timer_delay = 10;
	
	public static void main(String[] args) throws IOException {
		Game_manager gm = new Game_manager();
	}

	Game_manager() throws IOException {
		images = new HashMap<Character, Image>();
		sprites = new LinkedList<Sprite>();
		load_images();
		set_tile_dimension();
		load_tile_map();

		screen_manager = new Screen_manager();
		screen_manager.create_screen(tile_height * tiles.length * 16 / 9, tile_height * tiles.length);
		input_manager = new Input_manager(screen_manager);
		register_key_actions(input_manager);
		
		
		ActionListener timer_listerner = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				render_tile_map();	
			}
		};
		
		Timer timer = new Timer(Timer_delay, timer_listerner);
		timer.start();
		
	}

	private void register_key_actions(Input_manager input_manager) {
		Key_action action_left = new Key_action("action_left", KeyEvent.VK_LEFT) {
			
			@Override
			public void key_typed() {
				player.set_x_velocity(-0.5f);
			}
			
			@Override
			public void key_released() {
				player.set_x_velocity(0);
			}
			
			@Override
			public void key_pressed() {
				player.set_x_velocity(-0.5f);
			}
		};

		Key_action action_right = new Key_action("action_left", KeyEvent.VK_RIGHT) {
			
			@Override
			public void key_typed() {
				player.set_x_velocity(0.5f);
			}
			
			@Override
			public void key_released() {
				player.set_x_velocity(0);
			}
			
			@Override
			public void key_pressed() {
				player.set_x_velocity(0.5f);
			}
		};

		input_manager.register_key_action(action_left);
		input_manager.register_key_action(action_right);
	}
	
	public void load_tile_map() throws IOException {
		BufferedReader map_file = new BufferedReader(new FileReader("maps/map1.txt"));
		String line;
		LinkedList<String> lines = new LinkedList<String>();
		int max_line_length = 0;
		while ((line = map_file.readLine()) != null) {
			System.out.println(line);
			if (line.startsWith("#"))
				continue;
			lines.add(line);
			if (line.length() > max_line_length)
				max_line_length = line.length();  
		}
		map_file.close();
		
		tiles = new Image[lines.size()][max_line_length];
		for (int i=0; i < lines.size(); i++) {
			line = lines.get(i);
			for (int j=0; j < line.length(); j++) {
				char ch = line.charAt(j);
				if (ch >= 'A' && ch <= 'H') {
					tiles[i][j] = images.get(ch);
				}
				else {
					Image image = images.get(ch);
					if (image != null) {
						Sprite sprite = new Sprite(j * tile_width, i * tile_height, -0.2f, 0, image);
						sprites.add(sprite);
					}
				}
			}
		}
		
		player = new Sprite(100, 100, 0, 0, images.get('p'));
	}
	
	private void set_tile_dimension() {
		tile_width = images.get('A').getWidth(null);
		tile_height = images.get('A').getHeight(null);
	}
	
	void render_tile_map() {
		Graphics2D g2d = screen_manager.get_graphics();
		
		
		Image background = images.get('b');
		g2d.drawImage(background, 0, 0, null);
		player.update(Timer_delay);
		player.draw(g2d);
		//g2d.drawImage(new ImageIcon("images/player1.png").getImage(), 100, 100, null);
		
		for (int i = 0; i < tiles.length; i++)
			for (int j=0; j < tiles[0].length; j++)
				g2d.drawImage(tiles[i][j], j * tile_width, i * tile_height, null);
		for (Sprite s: sprites) {
			s.update(Timer_delay);
			s.draw(g2d);
		}
		g2d.dispose();
		screen_manager.show();
	}
	
	void load_images() {
	
		String image_path = "images/";
		images.put('o', new ImageIcon(image_path + "star1.png").getImage());
		images.put('1', new ImageIcon(image_path + "grub1.png").getImage());
		images.put('2', new ImageIcon(image_path + "fly1.png").getImage());
		images.put('*', new ImageIcon(image_path + "heart1.png").getImage());
		images.put('!', new ImageIcon(image_path + "music1.png").getImage());
		images.put('b', new ImageIcon(image_path + "background.png").getImage());
		images.put('p', new ImageIcon(image_path + "player1.png").getImage());
		
		for (char ch = 'A'; ch <= 'H'; ch++) {
			images.put(ch, new ImageIcon(image_path + "tile_" + ch + ".png").getImage());
		}
		
	}
}