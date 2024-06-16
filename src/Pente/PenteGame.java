package Pente;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PenteGame {
	
	public static JFrame gameFrame;
	public GamePanel gamePanel;
	public static JPanel navigationPanel = new JPanel();
	public static JPanel windowPanel = new JPanel();
	Dimension navPanDim = new Dimension(100,810);
	
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
		gameFrame.add(windowPanel);
		windowPanel.add(navigationPanel);
		navigationPanel.setPreferredSize(navPanDim);
		windowPanel.add(gamePanel);
		gamePanel.draw();
	}
}
