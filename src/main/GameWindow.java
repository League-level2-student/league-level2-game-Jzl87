package main;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow extends Window implements ActionListener {

	private GridLayout buttonLayout = new GridLayout(30, 30);
	protected Button[] playableButton;
	public static final int boardSize = 900;

	public static GameWindow gameWindow;

	public static Color black = Color.BLACK;
	public static Color border = Color.GRAY;

	public GameWindow(JFrame frame, JPanel panel) {
		super(frame, panel);
		windowSetup();
	}

	public void windowSetup() {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(buttonLayout);

		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
	}

	public void buttonSetup() {
		playableButton = new Button[boardSize];

		for (int x = 0; x < playableButton.length; x++) {

			if (x <= 29 || x >= 870) {
				playableButton[x] = playableButton[x] = new Button(true, x, border);
				playableButton[x].setBorderPainted(false);
				playableButton[x].setOpaque(true);
			} else {
				playableButton[x] = new Button(false, x, black);
				playableButton[x].addActionListener(this);
			}
			
			panel.add(playableButton[x]);

		}

		// score display 1
		playableButton[1].setText("p1");
		playableButton[1].setButtonColor(Color.WHITE);
		playableButton[2].setButtonColor(Color.WHITE);
		playableButton[2].setText(0 + "");

		// score display 2
		playableButton[5].setText("p2");
		playableButton[5].setButtonColor(Color.WHITE);
		playableButton[6].setButtonColor(Color.WHITE);
		playableButton[6].setText(0 + "");
	}
	
	public void addButtons (Button [] buttons) {
		for (Button button : buttons) {
		panel.add(button);
		}
	}
	
	public  Button[] getPlayableButton() {
		return playableButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
