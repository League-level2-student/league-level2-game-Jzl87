package main;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Panel extends JPanel{
	
	public JButton startButton = new JButton("Start Game?");
	public Button[][] playableButton = new Button[30][30];	
	
	public Panel (int currentState) {
		if(currentState == PenteRunner.MENU) {
			menuSetup();
		} else if (currentState == PenteRunner.GAME) {
			gameSetup();
		}else if (currentState == PenteRunner.END) {
			endSetup();
		}
	}
	
	private void menuSetup() {
		add(startButton);
	}
	
	private void gameSetup() {
		
	}
	
	private void endSetup() {
		add(startButton);
	}
}
