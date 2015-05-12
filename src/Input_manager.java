import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


public class Input_manager implements ActionListener {
	
	private Key_action[] key_action_array;
	private int pressed_key_code;
	private int released_key_code;
	private long current_time;
	private long elapsed_time;
	private Timer timer;
	private boolean pressed;
	
	private void print_elapsed_time() {
		System.out.println(elapsed_time);
	}
	
	private void update_time() {
		long new_time = System.currentTimeMillis();
		elapsed_time = new_time - current_time;
		current_time = new_time;
	}

	public Input_manager(Screen_manager screen_manager) {
		key_action_array = new Key_action[600];
		current_time = System.currentTimeMillis();
		timer = new Timer(5, this);
		timer.setRepeats(false);
		KeyListener key_listener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent key_event) {
			}
			
			@Override
			public void keyReleased(KeyEvent key_event) {
				pressed = false;
				released_key_code = key_event.getKeyCode();
				timer.start();
			}
			
			@Override
			public void keyPressed(KeyEvent key_event) {
				update_time();
				pressed_key_code = key_event.getKeyCode();
				timer.stop();
				
				
				//if (current_key_code != -1 && current_key_code != key_event.getKeyCode()) {
				if (pressed_key_code == released_key_code && !pressed) {
					return;
				}

				Key_action key_action;
				if (released_key_code != -1 && !pressed) {
					key_action = key_action_array[released_key_code];  
					if (!pressed && key_action != null)
						key_action.key_released();
				}
				
				key_action = key_action_array[pressed_key_code];  
				if (key_action != null)
					key_action.key_pressed();
				
				pressed = true;
			}
		};
		
		screen_manager.register_key_listener(key_listener);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (released_key_code == -1)
			return;
		Key_action key_action = key_action_array[released_key_code];
		if (key_action != null)
			key_action.key_released();
		released_key_code = -1;
	}
	
	public void register_key_action(Key_action key_action) {
		key_action_array[key_action.get_id()] = key_action;
	}
}
