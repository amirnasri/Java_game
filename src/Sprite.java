import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import Player.States;


public class Sprite {
	private String name;
	protected float x;
	protected float y;
	protected float v_x;
	protected float v_y;
	protected float x_new;
	protected float y_new;
	//private Image image;
	private float image_width;
	private float image_height;
	private static int display_x_offset;
	private Animation anim;
	
	public Sprite(String name, int x, int y, float v_x, float v_y, Animation anim) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.v_x = v_x;
		this.v_y = v_y;
		this.anim = anim;
		this.image_width = get_width();
		this.image_height = get_height();
	}
	
	public void set_anim(Animation anim) {
		this.anim = anim;
	}
	
	public static void set_display_x_offset(int offset) {
		display_x_offset = offset;	
	}

	public void update(int elapsed_time) {
		anim.update(elapsed_time);
		if (name.equals("f")) {
			v_x = (float) (v_x + elapsed_time * (Math.random() - 0.5) * 0.01); 
			v_y = (float) ((Math.random() - 0.5) * 1); 
		}
		x_new = x + v_x * elapsed_time;
		y_new = y + v_y * elapsed_time;
	}
	
	public void update_apply() {
		x = x_new;
		y = y_new;
	}
		
	public void draw(Graphics2D g2d) {
		g2d.drawImage(anim.get_image(), (int) x - display_x_offset, (int) y, null);
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
	
	public String get_name() {
		return name;
	}

	public Rectangle get_bounding_box() {
		//return new Rectangle(x + image_width/2, y + image_height/2, image_width, image_height);
		return new Rectangle((int) x, (int) y, (int) image_width, (int) image_height);
	}
	
	public float get_width() {
		return anim.get_width();
	}

	public float get_height() {
		return anim.get_height();
	}
	
	public void tile_collision_left() {
	}
	
	public void tile_collision_right() {
	}

	public void tile_collision_up() {
	}

	public void tile_collision_down() {
	}

}
