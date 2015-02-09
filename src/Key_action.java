
public abstract class Key_action {

	private int key_id;
	private String action_name;
	
	public Key_action(String action_name, int key_id) {
		this.key_id = key_id;
		this.action_name = action_name;
	}
	
	public int get_id() {
		return key_id;
	}
	
	public abstract void key_typed();
	public abstract void key_pressed();
	public abstract void key_released();
}
