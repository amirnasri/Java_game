import java.awt.Image;
import java.util.ArrayList;


public class Player extends Sprite {
	enum States {
		NORMAL, ON_GROUND, IN_AIR, IDLE
	}

	private static final int ArrayList = 0;

	private static final int Image = 0;
	
	private States state;
	
	public Player(int x, int y, float v_x, float v_y, Animation anim) {
		super(x, y, v_x, v_y, anim);
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

	public void jump() {
		v_y = -2;
		state = States.IN_AIR;
	}
}
