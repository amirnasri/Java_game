import java.awt.Image;
import java.util.ArrayList;


public class Player extends Sprite {
	enum States {
		NORMAL, ON_GROUND, IN_AIR, IDLE
	}

	private static final int ArrayList = 0;
	private static final int Image = 0;
	private States state;
	private int dir = 1;
	private Resource_manager res_manager;
	private ArrayList<Integer> dur_list = new ArrayList<>();
	private ArrayList<Integer> dur_list_still = new ArrayList<>();
	private ArrayList<Image> image_list_left = new ArrayList<Image>();
	private ArrayList<Image> image_list_right = new ArrayList<Image>();
	private ArrayList<Image> image_list_still_left = new ArrayList<Image>();
	private ArrayList<Image> image_list_still_right = new ArrayList<Image>();
	
	public Player(int x, int y, float v_x, float v_y, Animation anim, Resource_manager rm) {
		super(x, y, v_x, v_y, anim);
		this.res_manager = rm;

		dur_list.add(200);
		dur_list.add(200);
		
		image_list_left.add(res_manager.get_image("ml1"));
		image_list_left.add(res_manager.get_image("ml2"));

		image_list_right.add(res_manager.get_image("mr1"));
		image_list_right.add(res_manager.get_image("mr2"));

		image_list_still_left.add(res_manager.get_image("mls"));
		
		image_list_still_right.add(res_manager.get_image("mrs"));
		dur_list.add(200);
		
		state = States.IN_AIR;
	}

	public void update(int elapsed_time) {
		super.update(elapsed_time);
		//if (state == States.IN_AIR) {
		v_y = v_y + 0.1f;
		//}
		
		super.update(elapsed_time);
	}
	
	public void tile_collision_x() {
		v_x = 0;
	}
	
	public void tile_collision_y() {
		if (v_y > 0) {
			v_y = 0;
			state = States.ON_GROUND;
		}
	}

	public void set_v_x(float v_x) {
		if (Math.signum(dir) != Math.signum(v_x)) {
			if (Math.signum(v_x) < 0) {
				set_anim(new Animation(image_list_left, dur_list));
			}
			else if (Math.signum(v_x) > 0) {
				set_anim(new Animation(image_list_right, dur_list));
			}
			else {
				if (dir < 0)
					set_anim(new Animation(image_list_still_right, dur_list));
				else
					set_anim(new Animation(image_list_still_left, dur_list));
			}
			dir = (int) (-1 * Math.signum(v_x));
		}

		super.set_v_x(v_x);
	}

	public void jump() {
		v_y = -2;
		state = States.IN_AIR;
	}
}
