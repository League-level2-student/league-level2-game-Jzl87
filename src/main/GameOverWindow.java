package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverWindow extends Window implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	private JButton resetButton = new JButton("New Game?");
	private JLabel label;
	
	public GameOverWindow(JFrame frame, JPanel panel) {
		super(frame, panel);
		windowSetup();
		 label = new JLabel();
	}
	
	public void windowSetup() {
	
		
	//	label.setText(Who won?);
		panel.add(label);

		panel.add(resetButton);
		resetButton.addActionListener(this);
		
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		
		if (buttonPressed == resetButton) {
			//FILL CODE
			
		}
		
	}
}
