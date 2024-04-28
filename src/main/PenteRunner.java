package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PenteRunner {

	public static JFrame gameFrame = new JFrame("Pente");
	public static JPanel mainPanel = new JPanel();
	public static final int MENU = 0;
	public static final int GAME = 1;
	public static final int END = 2;

	public static int currentState = 0;

	public static void main(String[] args) {
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gameFrame.add(mainPanel);
		mainPanel.add(new Panel(currentState));
		
		gameFrame.pack();
		
	}
}
