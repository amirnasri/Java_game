import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class Resource_manager {

	private HashMap<String, Image> images;
	private Screen_manager screen_manager;
		
	Resource_manager(Screen_manager screen_manager) throws IOException {
		images = new HashMap<String, Image>();
		this.screen_manager = screen_manager;
		load_images();
	}
	
	private void load_images() throws IOException {
		String image_path = "images/";
		images.put("s", new ImageIcon(image_path + "star1.png").getImage());
		images.put("g1", new ImageIcon(image_path + "grub1.png").getImage());
		images.put("g2", new ImageIcon(image_path + "grub2.png").getImage());
		images.put("f1", new ImageIcon(image_path + "fly1.png").getImage());
		images.put("f2", new ImageIcon(image_path + "fly2.png").getImage());
		images.put("f3", new ImageIcon(image_path + "fly3.png").getImage());
		images.put("*", new ImageIcon(image_path + "heart1.png").getImage());
		images.put("!", new ImageIcon(image_path + "music1.png").getImage());
		images.put("b", new ImageIcon(image_path + "background.png").getImage());
		// Image player_image = new ImageIcon(image_path +
		// "mario.gif").getImage();
		// Image player_image =
		// Toolkit.getDefaultToolkit().createImage(image_path + "mario.gif");
		// Image player_image = ImageIO.read(new File(image_path +
		// "Mario_Big_Right_Still.png"));

		images.put("mls", get_scaled_image(new ImageIcon(image_path	+ "Mario_Big_Right_Still.png").getImage(), -3, 3));
		images.put("ml1", get_scaled_image(new ImageIcon(image_path	+ "Mario_Big_Right_1.png").getImage(), -3, 3));
		images.put("ml2", get_scaled_image(new ImageIcon(image_path	+ "Mario_Big_Right_2.png").getImage(), -3, 3));
		images.put("mrs", get_scaled_image(new ImageIcon(image_path	+ "Mario_Big_Right_Still.png").getImage(), 3, 3));
		images.put("mr1", get_scaled_image(new ImageIcon(image_path + "Mario_Big_Right_1.png").getImage(), 3, 3));
		images.put("mr2", get_scaled_image(new ImageIcon(image_path	+ "Mario_Big_Right_2.png").getImage(), 3, 3));
		// images.put("pl", player_image);

		// images.put('p', new ImageIcon(image_path + "mario.gif").getImage());
		// images.put('p', new ImageIcon(image_path +
		// "player1.png").getImage());

		for (char ch = 'A'; ch <= 'I'; ch++) {
			images.put(Character.toString(ch), new ImageIcon(image_path
					+ "tile_" + ch + ".png").getImage());
		}

	}
	
	public Image get_image(String name) {
		return images.get(name);
	}

	Image get_scaled_image(Image image, float x, float y) {
		Image newImage = screen_manager
				.get_frame()
				.getGraphicsConfiguration()
				.createCompatibleImage(
						(int) Math.abs(x) * image.getWidth(null),
						(int) Math.abs(y) * image.getHeight(null),
						Transparency.BITMASK);
		AffineTransform transform = new AffineTransform();
		transform.scale(x, y);
		//transform.translate((x-1) * image.getWidth(null) / 2, (y-1) *
		// image.getHeight(null) / 2);
		if (x < 0) {
			transform.translate(-image.getWidth(null), 0);
		}
		Graphics2D g = (Graphics2D) newImage.getGraphics();
		g.drawImage(image, transform, null);
		g.dispose();

		return newImage;
	}


}
