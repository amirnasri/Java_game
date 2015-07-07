
public class Player extends Sprite {
	enum States {
		NORMAL, ON_GROUND, IN_AIR, IDLE
	}

	private States state;
	private Resource_manager res_manager;
	/*
	private ArrayList<Integer> dur_list = new ArrayList<>();
	private ArrayList<Integer> dur_list_still = new ArrayList<>();
	private ArrayList<Image> image_list_left = new ArrayList<Image>();
	private ArrayList<Image> image_list_right = new ArrayList<Image>();
	private ArrayList<Image> image_list_still_left = new ArrayList<Image>();
	private ArrayList<Image> image_list_still_right = new ArrayList<Image>();
	*/
	
	Player(int x, int y, float v_x, float v_y, Animation anim, Resource_manager rm) {
		super("player", x, y, v_x, v_y, anim);
		this.res_manager = rm;
		
		/*
		dur_list.add(500);
		dur_list.add(500);
		
		image_list_left.add(res_manager.get_image("ml1"));
		image_list_left.add(res_manager.get_image("ml2"));

		image_list_right.add(res_manager.get_image("mr1"));
		image_list_right.add(res_manager.get_image("mr2"));

		image_list_still_left.add(res_manager.get_image("mls"));
		
		image_list_still_right.add(res_manager.get_image("mrs"));
		dur_list_still.add(200);
		*/
		
		state = States.IN_AIR;
	}

	void update(int elapsed_time) {
		super.update(elapsed_time);
		// update v_y
		// if (state == States.IN_AIR) {
		v_y = v_y + 0.1f;
		// }
		
		// update direction and image
		if (Math.signum(direction) != Math.signum(v_x)) {
			// System.out.println("dir changed from " + Math.signum(dir) + "to "
			// + Math.signum(v_x));
			if (Math.signum(v_x) < 0) {
				// set_anim(new Animation(image_list_left, dur_list));
				set_anim(res_manager.get_anim("mario_run_left"));
			} else if (Math.signum(v_x) > 0) {
				set_anim(res_manager.get_anim("mario_run_right"));
			} else {
				if (direction < 0)
					set_anim(res_manager.get_anim("mario_still_left"));
				else
					set_anim(res_manager.get_anim("mario_still_right"));
			}
			direction = (int) Math.signum(v_x);
		}
	}
	
	public void tile_collision_left() {
		set_v_x(0);
	}
	
	public void tile_collision_right() {
		set_v_x(0);
	}

	public void tile_collision_down() {
		set_v_y(0);
		state = States.ON_GROUND;
	}

	public void jump() {
		if (state == States.ON_GROUND) {
			v_y = -2.5f;
			state = States.IN_AIR;
		}
	}
	
	public States get_state() {
		return state;
	}
}
