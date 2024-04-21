package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PenteRunner {

	public static JFrame gameFrame = new JFrame("Pente");
	public static JPanel panel;
	private static final int MENU = 0;
	private static final int GAME = 1;
	private static final int END = 2;

	public static int currentState = 2;
	
	public static void main(String[] args) {
		gameFrame.setVisible(true);
		setup();
		
	}
	
	public static void setup() {
		
		panel = new JPanel();

		GameWindow gameWindow;
		GameOverWindow gameOverWindow;

		if (currentState == GAME) {
			gameWindow = new GameWindow(gameFrame, panel);
			gameWindow.buttonSetup();
			gameWindow.addButtons(gameWindow.getPlayableButton());
		} else if (currentState == END){
			gameOverWindow = new GameOverWindow(gameFrame, panel);

		}

	}
}
