import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.ImageIcon;

public class Game_manager {

	private LinkedList<Sprite> sprites;
	private Tiles tiles;
	private Player player;
	private Screen_manager screen_manager;
	private Input_manager input_manager;
	private Resource_manager res_manager;
	private int tile_width;
	private int tile_height;
	// Width of the entire game
	private int total_display_width;
	// Width and height of current game display
	private int display_width;
	private int display_height;
	private int display_x_min;
	private int display_x_max;
	long current_time;

	private final static int Timer_delay = 10;

	private class Tiles {
		private Image[][] tiles;
		private int dim1;
		private int dim2;

		Tiles(int dim1, int dim2) {
			this.dim1 = dim1;
			this.dim2 = dim2;
			tiles = new Image[dim1][dim2];
		}

		void set_tile(int i, int j, Image image) {
			if (i < 0 || j < 0 || i >= dim1 || j >= dim2)
				return;
			tiles[i][j] = image;
		}

		Image get_tile(int i, int j) {
			if (i < 0 || j < 0 || i >= dim1 || j >= dim2)
				return null;

			return tiles[i][j];
		}

		int get_dim(int dim) {
			if (dim == 1)
				return dim1;
			if (dim == 2)
				return dim2;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		Game_manager gm = new Game_manager();
	}

	Game_manager() throws IOException {
		sprites = new LinkedList<Sprite>();
		screen_manager = new Screen_manager();
		res_manager = new Resource_manager(screen_manager);
		//load_images();
		load_game_map();

		total_display_width = tile_width * tiles.get_dim(1);
		display_height = tile_height * tiles.get_dim(2);
		display_width = display_height * 16 / 9;
		System.out.println(display_width);
		System.out.println(display_height);
		screen_manager.create_screen(display_width, display_height);
		input_manager = new Input_manager(screen_manager);
		register_key_actions(input_manager);

		/*
		 * ActionListener timer_listerner = new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * render_tile_map(); } };
		 * 
		 * Timer timer = new Timer(Timer_delay, timer_listerner); timer.start();
		 */
		current_time = 0;
		game_loop();
	}

	void game_loop() {

		int elapsed_time = 0;
		long new_time;

		while (true) {
			new_time = System.currentTimeMillis();
			if (current_time != 0)
				elapsed_time = (int) (new_time - current_time);
			current_time = new_time;
			update(elapsed_time);
			check_collision(elapsed_time);

			render_display();

			try {
				Thread.sleep(Timer_delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private boolean check_collision(int elapsed_time) {

		boolean collision = false;

		Rectangle player_bb = player.get_bounding_box();

		// check collision between the player and tiles

		float dy = player.get_v_y() * elapsed_time;
		if (dy > 0) {
			int cur_tile_x = (int) Math.floor((player.get_x() + player
					.get_width() / 2) / tile_width);
			int cur_tile_y = (int) Math.floor((player.get_y() + player
					.get_height()) / tile_height);

			// int new_tile_x = (int) Math.floor((player.get_x_new() +
			// player.get_width()/2)/tile_width);
			int new_tile_y = (int) Math.floor((player.get_y() + dy + player
					.get_height()) / tile_height);

			for (int t = cur_tile_y; t <= new_tile_y; t++) {
				if (tiles.get_tile(cur_tile_x, t) != null) {
					player.set_x(player.get_x() + player.get_v_x()
							* elapsed_time);
					player.set_y(t * tile_height - player.get_height());
					player.tile_collision_y();
					collision = true;
					break;
				}

			}
		}

		float dx = player.get_v_x() * elapsed_time;
		int cur_tile_y = (int) Math
				.floor((player.get_y() + player.get_height() / 2) / tile_height);
		if (dx > 0) {
			int cur_tile_x = (int) Math.floor((player.get_x() + player
					.get_width()) / tile_width);
			int new_tile_x = (int) Math.floor((player.get_x() + dx + player
					.get_width()) / tile_width);

			for (int t = cur_tile_x; t <= new_tile_x; t++) {
				if (t > tiles.get_dim(1) - 1
						|| tiles.get_tile(t, cur_tile_y) != null) {
					player.set_y(player.get_y() + player.get_v_y()
							* elapsed_time);
					player.set_x(t * tile_width - player.get_width());
					player.tile_collision_x();
					collision = true;
					break;
				}

			}
		} else {
			int cur_tile_x = (int) Math.floor((player.get_x()) / tile_width);
			int new_tile_x = (int) Math.floor((player.get_x() + dx)
					/ tile_width);

			for (int t = cur_tile_x; t >= new_tile_x; t--) {
				if (t < 0 || tiles.get_tile(t, cur_tile_y) != null) {
					player.set_y(player.get_y() + player.get_v_y()
							* elapsed_time);
					player.set_x((t + 1) * tile_width);
					player.tile_collision_x();
					collision = true;
					break;
				}

			}
		}
		/*
		 * for (int i=Math.max(display_x_min/tile_width, 0); i <
		 * Math.min(display_x_max/tile_width, tiles.get_dim(1)); i++) for (int j
		 * = 0; j < tiles.get_dim(2); j++) { Image tile = tiles.get_tile(i, j);
		 * if (tile != null) { Rectangle tile_bounding_box = new Rectangle((int)
		 * i * tile.getWidth(null), (int) j * tile.getHeight(null),
		 * tile.getWidth(null), tile.getHeight(null));
		 * 
		 * if (player_bb.intersects(tile_bounding_box)) {
		 * //System.out.println("Collision!!!!!"); player.tile_collision();
		 * return true; }
		 * 
		 * } }
		 */

		// player.update_apply();
		if (!collision) {
			player.set_x(player.get_x() + dx);
			player.set_y(player.get_y() + dy);
		}
		return false;
	}
	private int count;
	private void register_key_actions(Input_manager input_manager) {
		Key_action action_left = new Key_action("action_left", KeyEvent.VK_LEFT) {

			@Override
			public void key_released() {
				player.set_v_x(0);
				System.out.println(count++ + " Left released!!!");
			}

			@Override
			public void key_pressed() {
				System.out.println(count++ + " Left pressed!!!");
				player.set_v_x(-0.5f);
			}
		};

		Key_action action_right = new Key_action("action_right", KeyEvent.VK_RIGHT) {

			@Override
			public void key_released() {
				player.set_v_x(0);
				System.out.println(count++ + "Right released!!!");
			}

			@Override
			public void key_pressed() {
				System.out.println(count++ + " Right pressed!!!");
				player.set_v_x(0.5f);
			}
		};

		Key_action action_up = new Key_action("action_up", KeyEvent.VK_UP) {

			@Override
			public void key_released() {
				// player.set_v_y(0);
			}

			@Override
			public void key_pressed() {
				player.jump();
			}
		};

		Key_action action_down = new Key_action("action_down", KeyEvent.VK_DOWN) {

			@Override
			public void key_released() {
				player.set_v_y(0);
			}

			@Override
			public void key_pressed() {
				player.set_v_y(0.5f);
			}
		};

		input_manager.register_key_action(action_left);
		input_manager.register_key_action(action_right);
		input_manager.register_key_action(action_up);
		input_manager.register_key_action(action_down);
	}

	public void load_game_map() throws IOException {

		set_tile_dimension();

		BufferedReader map_file = new BufferedReader(new FileReader(
				"maps/map1.txt"));
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

		tiles = new Tiles(max_line_length, lines.size());
		for (int j = 0; j < lines.size(); j++) {
			line = lines.get(j);
			for (int i = 0; i < line.length(); i++) {
				char ch = line.charAt(i);
				if (ch >= 'A' && ch <= 'H') {
					tiles.set_tile(i, j, res_manager.get_image(Character.toString(ch)));
				} else {
					Animation anim;
					switch (ch) {
					case 'f':
						anim = get_anim("fly");
						break;

					case 'g':
						anim = get_anim("grub");
						break;

					default:
						anim = get_anim(Character.toString(ch));
						break;
					}

					//Sprite sprite = new Sprite(i * tile_width, j * tile_height,
					//		-0.1f, 0, anim);
					//sprites.add(sprite);

					tiles.set_tile(i, j, null);
				}
			}
		}

		player = new Player(100, 100, 0, 0, get_anim("mario_run"), res_manager);
	}

	private void set_tile_dimension() {
		tile_width = res_manager.get_image("A").getWidth(null);
		tile_height = res_manager.get_image("A").getHeight(null);
	}

	void update(int elapsed_time) {
		player.update(elapsed_time);

		for (Sprite s : sprites) {
			s.update(elapsed_time);
			s.update_apply();
		}
	}

	void render_display() {

		Graphics2D g2d = screen_manager.get_graphics();

		Image background = res_manager.get_image("b");
		g2d.drawImage(background, 0, 0, null);

		int player_x = (int) player.get_x();
		display_x_min = Math.min(total_display_width - display_width,
				Math.max(0, player_x - display_width / 2));
		// display_x_max = display_x_min + display_width + 2 * tile_width;
		display_x_max = display_x_min + display_width;
		// int offset = Math.min(total_display_width - display_width,
		// Math.max(0, player_x - display_width/2));
		int offset;
		if (player_x < display_width / 2)
			offset = 0;
		else if (player_x > total_display_width - display_width / 2)
			// offset = display_width - (total_display_width - player_x);
			offset = total_display_width - display_width;
		else
			offset = player_x - display_width / 2;

		Sprite.set_display_x_offset(offset);

		player.draw(g2d);

		for (int i = Math.max(display_x_min / tile_width, 0); i <= Math.min(
				display_x_max / tile_width, tiles.get_dim(1)); i++)
			for (int j = 0; j < tiles.get_dim(2); j++) {
				// System.out.println(i +"  " + j);
				g2d.drawImage(tiles.get_tile(i, j), i * tile_width - offset, j
						* tile_height, null);
			}

		for (Sprite s : sprites)
			s.draw(g2d);

		g2d.dispose();
		screen_manager.show();
	}
	
	private Animation get_anim(String anim_name) {

		ArrayList<Integer> dur_list;
		ArrayList<Image> image_list;
		switch (anim_name) {
		case "fly":
			image_list = new ArrayList<Image>();
			image_list.add(res_manager.get_image("f1"));
			image_list.add(res_manager.get_image("f2"));
			image_list.add(res_manager.get_image("f3"));
			dur_list = new ArrayList<>();
			dur_list.add(50);
			dur_list.add(50);
			dur_list.add(50);
			break;
		case "grub":
			image_list = new ArrayList<Image>();
			image_list.add(res_manager.get_image("g1"));
			image_list.add(res_manager.get_image("g2"));
			dur_list = new ArrayList<>();
			dur_list.add(100);
			dur_list.add(100);
			break;
		case "mario_run":
			image_list = new ArrayList<Image>();
			image_list.add(res_manager.get_image("mr1"));
			image_list.add(res_manager.get_image("mr2"));
			dur_list = new ArrayList<>();
			dur_list.add(200);
			dur_list.add(200);
			break;
		default:
			image_list = new ArrayList<Image>();
			image_list.add(res_manager.get_image(anim_name));
			dur_list = new ArrayList<>();
			dur_list.add(50);
		}
		return new Animation(image_list, dur_list);
	}
}