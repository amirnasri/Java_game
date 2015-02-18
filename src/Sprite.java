import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class Sprite {
	protected float x;
	protected float y;
	protected float dx;
	protected float dy;
	private Image image;
	private int image_width;
	private int image_height;
	private static int display_x_offset;
	
	public Sprite(int x, int y, float dx, float dy, Image image) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.image = image;
		this.image_width = image.getWidth(null);
		this.image_height = image.getHeight(null);
	}
	
	public static void set_display_x_offset(int offset) {
		display_x_offset = offset;	
	}

	public void update(int elapsed_time) {
		x = x + dx * elapsed_time;
		y = y + dy * elapsed_time;
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x - display_x_offset, (int) y, null);
	}
	
	public void set_x_velocity(float dx) {
		this.dx = dx;
	}

	public void set_y_velocity(float dy) {
		this.dy = dy;
	}
	
	public int get_x() {
		return (int) x;
	}
	
	public Rectangle get_bounding_box() {
		//return new Rectangle(x + image_width/2, y + image_height/2, image_width, image_height);
		return new Rectangle((int) x, (int) y, image_width, image_height);
	}
}
