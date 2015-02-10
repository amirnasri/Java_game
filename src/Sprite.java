import java.awt.Graphics2D;
import java.awt.Image;


public class Sprite {
	protected float x;
	protected float y;
	protected float dx;
	protected float dy;
	private Image image;
	private static int display_x_offset;
	
	public Sprite(int x, int y, float dx, float dy, Image image) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.image = image;
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
	
	public static void set_display_x_offset(int offset) {
		display_x_offset = offset;	
	}
}
