import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Screen_manager {

	private Canvas canvas;

	Screen_manager() {

		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        frame.setSize(800, 600);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(800, 600));
		frame.add(canvas);

		//Container content_pane = frame.getContentPane();
		//content_pane.setLayout(new GridLayout(1, 1));
		
		
		frame.setVisible(true);
		canvas.createBufferStrategy(2);


	}

	public Graphics2D get_graphics() {
		return (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
	}
	
	public void show() {
		canvas.getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
	}
}