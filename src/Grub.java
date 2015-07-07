
public class Grub extends Sprite {

	static Resource_manager res_manager;
	
	Grub(String name, int x, int y, float v_x, float v_y, Animation anim, Resource_manager res_manager) {
		super(name, x, y, v_x, v_y, anim);
		Grub.res_manager = res_manager; 
	}

	void update(int elapsed_time) {
		super.update(elapsed_time);
		v_y = v_y + 0.1f;
		
		if (Math.signum(direction) != Math.signum(v_x)) {
			// System.out.println("dir changed from " + Math.signum(dir) + "to "
			// + Math.signum(v_x));
			if (Math.signum(v_x) < 0) {
				// set_anim(new Animation(image_list_left, dur_list));
				set_anim(res_manager.get_anim("grub_left"));
			} else  {
				set_anim(res_manager.get_anim("grub_right"));
			}
			direction = (int) Math.signum(v_x);
		}

	}
}
