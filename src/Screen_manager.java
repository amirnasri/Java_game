import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Screen_manager {

	private Canvas canvas;
	private JFrame frame;
	
	Screen_manager() {
		frame = new JFrame();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        //frame.setIgnoreRepaint(true);
        //frame.setResizable(false);
	}

	public void create_screen(int screen_width, int screen_height) {
        frame.setSize(screen_width, screen_height);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(screen_width, screen_height));
		frame.add(canvas);
		frame.setVisible(true);

		//Container content_pane = frame.getContentPane();
		//content_pane.setLayout(new GridLayout(1, 1));
		
		canvas.createBufferStrategy(2);
	}
	
	public Graphics2D get_graphics() {
		return (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
	}

	public void set_screen_size(int screen_width, int screen_height) {
		canvas.setPreferredSize(new Dimension(screen_width, screen_height));
	}

	public void show() {
		canvas.getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
	}
	
	public void register_key_listener(KeyListener key_listener) {
		frame.addKeyListener(key_listener);
	}
	
	public JFrame get_frame() {
		return frame;
	}
}