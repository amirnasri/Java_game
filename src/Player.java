import java.awt.Image;


public class Player extends Sprite {
	enum States {
		NORMAL, ON_GROUND, IN_AIR, IDLE
	}
	
	private States state;
	
	public Player(int x, int y, float dx, float dy, Image image) {
		super(x, y, dx, dy, image);
		state = States.IN_AIR;
	}

	public void update(int elapsed_time) {
		//if (state == States.IN_AIR) {
		dy = dy + 0.1f;
		//}
		
		super.update(elapsed_time);
	}
	
	public void tile_collision() {
		if (dy > 0) {
			dy = 0;
			state = States.ON_GROUND;
		}
	}
	
	public void jump() {
		dy = -2;
		state = States.IN_AIR;
	}
}
