package heavenland;

import javax.swing.SwingUtilities;

import heavenland.resource.Loader;
import heavenland.framework.Engine;

public class Launcher {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				Loader.load();
				Engine engine = new Engine();
				engine.init();
				engine.start();
			}
		});
	}
}
