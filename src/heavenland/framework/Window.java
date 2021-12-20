package heavenland.framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame frame;
	protected JPanel panel;
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 672;
	public static final int CENTER_SCREEN_X = WIDTH/2;
	public static final int CENTER_SCREEN_Y = HEIGHT/2;
	
	public static final int SCALE = 3;
	
	public Window() {
		
		this.frame = new JFrame("Heaven Land");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
	}
	
	public void addPanel(JPanel panel) {
		
		this.panel = panel;
		this.panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.panel.setBackground(Color.black);
	}
	
	public void addListener(KeyListener keyListener, MouseListener mouseListener) {
		
		this.panel.addKeyListener(keyListener);
		this.panel.addMouseListener(mouseListener);
		this.panel.setFocusable(true);
	}
	
	public void createWindow() {
		
		this.frame.setContentPane(panel);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

}
