package Pente;

import javax.swing.JFrame;

public class PenteGame {
	
	public static JFrame gameFrame;
	public GamePanel gamePanel;
	
	public static void main(String[] args) {
		PenteGame game = new PenteGame();
		game.setup();
	}
	
	public PenteGame() {
		gameFrame = new JFrame("Pente");
		gamePanel = new GamePanel();
	}
	
	public void setup() {
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.add(gamePanel);
		gamePanel.draw();
	}
}
