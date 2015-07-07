
public class Fly extends Sprite {

	Fly(String name, int x, int y, float v_x, float v_y, Animation anim) {
		super(name, x, y, v_x, v_y, anim);
	}
	
	void update(int elapsed_time) {
		super.update(elapsed_time);
		v_x = (float) (v_x + elapsed_time * (Math.random() - 0.5) * 0.01); 
		v_y = (float) (elapsed_time * (Math.random() - 0.5) * 0.01); 
	}

}
