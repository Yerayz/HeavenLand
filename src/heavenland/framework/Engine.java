package heavenland.framework;

public class Engine implements Runnable {

	private Window window;
	private Thread gameThread;
	
	public void init() {
		
		this.window = new Window();
		this.gameThread = new Thread(this);
	}
	
	public void start() {
		
		this.window.addPanel(new GamePanel());
		this.window.addKeyListener(new KeyHandler());
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
				tick();
				TPS++;
				delta--;
			}
			this.window.render();
			FPS++;
			
			if(timer >= 1000000000) {
				System.out.println("TPS: " + TPS + " | FPS: " + FPS);
				TPS = 0;
				FPS = 0;
				timer = 0;
			}
		}
	}
	
	public void tick() {
		
		
	}
	
}
