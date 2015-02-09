import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input_manager {
	
	private Key_action[] key_action_array;
	
	public Input_manager(Screen_manager screen_manager) {
		key_action_array = new Key_action[600];
		KeyListener key_listener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent key_event) {
				Key_action key_action = key_action_array[key_event.getKeyCode()];  
				if (key_action != null)
					key_action.key_typed();
			}
			
			@Override
			public void keyReleased(KeyEvent key_event) {
				Key_action key_action = key_action_array[key_event.getKeyCode()];  
				if (key_action != null)
					key_action.key_released();
			}
			
			@Override
			public void keyPressed(KeyEvent key_event) {
				Key_action key_action = key_action_array[key_event.getKeyCode()];  
				if (key_action != null)
					key_action.key_pressed();
			}
		};
		
		screen_manager.register_key_listener(key_listener);
	}

	public void register_key_action(Key_action key_action) {
		key_action_array[key_action.get_id()] = key_action;
	}
}
