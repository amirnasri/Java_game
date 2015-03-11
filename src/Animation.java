import java.awt.Image;
import java.util.ArrayList;


public class Animation {
	private ArrayList<Image> frame_list;
	private ArrayList<Integer> anim_acc_dur_list;
	private int cur_time;
	private int frame_index;
	private int total_dur;
	private int frame_len;
	protected Image image;
	
	public Animation(ArrayList<Image> frame_list, ArrayList<Integer> frame_dur_list) {
		this.frame_list = frame_list;
		int acc_t = 0;
		anim_acc_dur_list = new ArrayList<>();
		for (int t: frame_dur_list) {
			acc_t += t;
			anim_acc_dur_list.add(acc_t);
		}
		frame_len = anim_acc_dur_list.size();
		total_dur = anim_acc_dur_list.get(frame_len);
		cur_time = 0;
		frame_index = 0;
	}
	
	public void update(int elapsed_time) {
		cur_time += elapsed_time;
		if (cur_time >= anim_acc_dur_list.get(frame_index)) {
			frame_index++;
			if (frame_index == frame_len) {
				frame_index = 0;
			}
			image = frame_list.get(frame_index);
			cur_time = cur_time % total_dur;
		}
	}
	
	public Image get_image() {
		return image;
	}

	public float get_width() {
		return image.getWidth(null);
	}

	public float get_height() {
		return image.getHeight(null);
	}

}
