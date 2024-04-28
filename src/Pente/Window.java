package Pente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	JPanel menuPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel endPanel = new JPanel();
	static JFrame frame = Runner.frame;
	JButton startButton = new JButton("start game");
	JButton restartButton = new JButton("restart game");

	int currentState;
	public Window(int currentState) {
		this.currentState = currentState;
		
	}

	public void setup() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (currentState == Runner.MENU) {
			frame.add(menuPanel);
			menuPanel.add(Runner.startButton);
		} else if (currentState == Runner.GAME) {
			frame.add(gamePanel);

		} else if (currentState == Runner.END) {
			frame.add(endPanel);
			endPanel.add(restartButton);
		}
		frame.pack();
	}

}
