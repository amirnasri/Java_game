import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Sprite extends Animation {
	protected float x;
	protected float y;
	protected float v_x;
	protected float v_y;
	protected float x_new;
	protected float y_new;
	//private Image image;
	private int image_width;
	private int image_height;
	private static int display_x_offset;
	
	public Sprite(int x, int y, float v_x, float v_y, ArrayList<Image> frame_list, ArrayList<Integer> frame_dur_list) {
		super(frame_list, frame_dur_list);
		this.x = x;
		this.y = y;
		this.v_x = v_x;
		this.v_y = v_y;
		//this.image = image;
	}
	
	public static void set_display_x_offset(int offset) {
		display_x_offset = offset;	
	}

	public void update(int elapsed_time) {
		x_new = x + v_x * elapsed_time;
		y_new = y + v_y * elapsed_time;
	}
	
	public void update_apply() {
		x = x_new;
		y = y_new;
	}
		
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x - display_x_offset, (int) y, null);
	}
	
	public float set_x(float x) {
		return this.x = x;
	}
	
	public float set_y(float y) {
		return this.y = y;
	}
	
	public float get_x() {
		return x;
	}
	
	public float get_y() {
		return y;
	}

	public void set_v_x(float v_x) {
		this.v_x = v_x;
	}

	public void set_v_y(float v_y) {
		this.v_y = v_y;
	}

	public float get_v_x() {
		return v_x;
	}

	public float get_v_y() {
		return v_y;
	}

	public Rectangle get_bounding_box() {
		//return new Rectangle(x + image_width/2, y + image_height/2, image_width, image_height);
		return new Rectangle((int) x, (int) y, image_width, image_height);
	}
}
