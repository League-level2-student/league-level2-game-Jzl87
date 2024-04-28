package Pente;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Runner {

	public final static int MENU = 0;
	public final static int GAME = 1;
	public final static int END = 2;

	public static int currentState = 2;

	public static JFrame frame = new JFrame("Pente");
	static JPanel menuPanel = new JPanel();
	static JPanel gamePanel = new JPanel();
	static JPanel endPanel = new JPanel();

	static 	JButton startButton = new JButton("start game");
	static JButton restartButton = new JButton("restart game");

	public static void main(String[] args) {
		
		}

	
}
