import java.awt.Graphics2D;
import java.awt.Image;


public class Sprite {
	private float x;
	private float y;
	private float dx;
	private float dy;
	private Image image;
	
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
		g2d.drawImage(image, (int) x, (int) y, null);
	}
	
	public void set_x_velocity(float dx) {
		this.dx = dx;
	}

	public void set_y_velocity(float dy) {
		this.dy = dy;
	}
}
