package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	public JPanel panel;
	protected JFrame frame = PenteRunner.gameFrame;

	public Window(JFrame frame, JPanel panel) {

		this.frame = frame;
		this.panel = new JPanel();
		windowSetup();
	}

	public void windowSetup() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		frame.pack();
	}

	public void closeWindow() {
		frame.dispose();
	}
}
