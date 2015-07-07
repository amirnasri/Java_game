
public class Sprite_factory {
	
	static Resource_manager res_manager;
	public Sprite_factory(Resource_manager res_manager) {
		Sprite_factory.res_manager = res_manager; 
	}
	
	public static Sprite make(char sprite_name) {
		Animation anim;
		Sprite sprite = null;
		switch (sprite_name) {
		case 'f':
			anim = res_manager.get_anim("fly");
			sprite = new Fly(String.valueOf(sprite_name) , 0, 0, 0, 0, anim);
			break;

		case 'g':
			anim = res_manager.get_anim("grub_left");
			sprite = new Grub(String.valueOf(sprite_name) , 0, 0, 0, 0, anim, res_manager);
			break;

		case ' ':
			anim = null;
			break;
			
		default:
			anim = res_manager.get_anim(Character.toString(sprite_name));
			sprite = new Sprite(String.valueOf(sprite_name) , 0, 0, 0, 0, anim);
			break;
		}
		
		return sprite;
	}
}
