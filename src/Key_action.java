import java.awt.event.ActionListener;

import javax.swing.Timer;

public abstract class Key_action implements ActionListener{

	private int key_id;
	private String action_name;
	private Timer timer;
	
	public Key_action(String action_name, int key_id) {
		this.key_id = key_id;
		this.action_name = action_name;
		timer = new Timer(5, this);
		timer.setRepeats(false);
	}
	
	public int get_id() {
		return key_id;
	}
	
	public void key_pressed() {
		timer.stop();
	}
	public void key_released() {
		timer.start();
	}
}
