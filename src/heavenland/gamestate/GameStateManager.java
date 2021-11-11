package heavenland.gamestate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateManager {

private Stack<GameState> states;
	
	public GameStateManager() {
		
		this.states = new Stack<>();
	}
	
	public void addState(GameState state) {
		
		this.states.add(state);
	}
	
	public void backToPreviousState() {
		
		this.states.pop();
	}
	
	public void clearState() {
		
		this.states.clear();
	}

	public void tick() {
		
		try {
			
			this.states.peek().tick();
			
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}

	public void render(Graphics2D g2d) {
		
		try {
			
			this.states.peek().render(g2d);
			
		} catch(EmptyStackException e) {
			System.err.println("[GameStateManager]: Error! GameState stack is empty!");
			System.exit(-1);
		}
	}
}
