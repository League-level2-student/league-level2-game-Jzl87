package Pente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public static final Color admin = Color.LIGHT_GRAY;

	// TURN VARIABLE
	public static boolean isPlayerOne;
	public static int roundWinner;

	// PENTE STATES
	public static final int menuState = 0;
	public static final int gameState = 1;
	public static final int endState = 2;
	public static int currentState;

	// SETUP/DIMENSIONS
	public static int boardDim = 30;
	GridLayout borderLayout = new GridLayout(30, 30);
	Font font = new Font("Arial", Font.BOLD, 12);

	JLabel score1Label = new JLabel();
	JLabel score2Label = new JLabel();
	JLabel winnerLabel = new JLabel();

	Dimension adminDim = new Dimension(100, 25);
	Dimension gameDim = new Dimension(810, 810);
	Dimension nonexistence = new Dimension(0, 0);

	// BOARD BUTTONS
	public JButton startButton;
	public JButton[] topBorder;
	public static GameButton[][] board;

	public GamePanel() {
		navigationPanelSetup();
		currentState = menuState;

	}

	public void draw() {
		if (currentState == gameState) {
			drawGame();
		} else if (currentState == endState) {
			drawEnd();
		} else {
			drawMenu();
		}
	}

	public void updateState() {
		currentState++;
		if (currentState >= 3) {
			currentState = 0;
		}
		draw();
	}

	private void drawMenu() {
		PenteGame.navigationPanel.remove(winnerLabel);
		setBackground(Color.WHITE);
		startButton.setText("Start?");

		PenteGame.gameFrame.pack();
	}

	private void drawGame() {
		setPreferredSize(gameDim);
		setLayout(borderLayout);

		setBackground(Color.GRAY);
		startButton.setText("Exit");

		isPlayerOne = true;
		boardSetup();

		PenteGame.navigationPanel.add(score1Label);
		PenteGame.navigationPanel.add(score2Label);

		score1Label.setText("P1 Captures: 0");
		score2Label.setText("P2 Captures: 0");
		PenteGame.gameFrame.pack();
	}

	private void drawEnd() {
		removeBoard();
		PenteGame.navigationPanel.remove(score1Label);
		PenteGame.navigationPanel.remove(score2Label);
		setPreferredSize(nonexistence);

		setBackground(Color.BLACK);
		startButton.setText("Menu");
		PenteGame.navigationPanel.add(winnerLabel);
		String labelText;
		if (!(roundWinner == 0)) {
			labelText = "<html>Player " + roundWinner + " wins!<br>";

			if (GameButton.p1Captures >= 5 || GameButton.p2Captures >= 5) {
				labelText += "via captures";
			} else {
				labelText += "via 5 in a row";
			}

		} else {
			labelText = "<html>Game's been quit.<br>New Game?";
		}
		winnerLabel.setText(labelText);
		PenteGame.gameFrame.pack();
	}

	private void removeBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				remove(board[row][col]);
			}
		}
	}

	private void navigationPanelSetup() {
		startButton = new JButton("Start!");
		startButton.setFont(font);
		startButton.addActionListener(this);
		startButton.setBackground(admin);
		startButton.setSize(adminDim);
		PenteGame.navigationPanel.add(startButton);
	}

	private void boardSetup() {
		board = new GameButton[boardDim][boardDim];
		GameButton.resetCaptures();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				board[row][col] = new GameButton(GameButton.openSpace);
				board[row][col].addActionListener(this);
				add(board[row][col]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();

		if (buttonPressed == startButton) {
			updateState();
		}

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (!board[row][col].isLocked() && buttonPressed == board[row][col]) {
					if (isPlayerOne) {

						board[row][col].checkCapture(row, col, isPlayerOne);

						score1Label.setText("P1 Captures: " + GameButton.p1Captures);
						score2Label.setText("P2 Captures: " + GameButton.p2Captures);

						board[row][col].setP1();
						isPlayerOne = false;

						// Check for p1 win
						if (board[row][col].checkFive(row, col, 1) || GameButton.p1Captures >= 5) {
							roundWinner = 1;
							updateState();
							
						}

					} else {

						board[row][col].checkCapture(row, col, isPlayerOne);

						score1Label.setText("P1 Captures: " + GameButton.p1Captures);
						score2Label.setText("P2 Captures: " + GameButton.p2Captures);

						board[row][col].setP2();
						isPlayerOne = true;

						// Check for p2 win
						if (board[row][col].checkFive(row, col, 2) || GameButton.p2Captures >= 5) {
							roundWinner = 2;
							updateState();
							
						}
					}
				}
			}
		}
	}
}
