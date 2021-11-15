package heavenland.framework;

import heavenland.gamestate.GameStateManager;
import heavenland.gamestate.Playing;
import heavenland.resource.Loader;

public class Engine implements Runnable {

	private GameStateManager gameStateManager;
	private GamePanel gamePanel;
	private KeyHandler keyH;
	private Loader loader;
	private Thread gameThread;
	private Window window;
	
	
	public void init() {
		
		this.loader = new Loader();
		this.loader.load();
		this.gameStateManager = new GameStateManager();
		this.gamePanel = new GamePanel(gameStateManager);
		this.keyH = new KeyHandler();
		this.window = new Window();
		this.gameThread = new Thread(this);
	}
	
	public void start() {
		
		this.gameStateManager.addState(new Playing(gameStateManager, keyH));
		this.window.addPanel(gamePanel);
		this.window.addKeyListener(keyH);
		this.window.createWindow();
		
		this.gameThread.start();
	}

	@Override
	public void run() {
		int ticks = 60;
		double drawInterval = 1000000000/ticks;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int TPS = 0;
		int FPS = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				this.gameStateManager.tick();
				TPS++;
				delta--;
			}
			this.gamePanel.repaint();
			FPS++;
			
			if(timer >= 1000000000) {
				System.out.println("TPS: " + TPS + " | FPS: " + FPS);
				TPS = 0;
				FPS = 0;
				timer = 0;
			}
		}
	}
}
