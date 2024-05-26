package Pente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	public static final Color admin = Color.LIGHT_GRAY;
	public static final Color border = Color.DARK_GRAY;
	
	//TURN VARIABLE
	public static boolean isPlayerOne;
	//PENTE STATES
	private static final int menuState = 0;
	private static final int gameState = 1;
	private static final int endState = 2;
	public static int currentState;
	
	//DIMENSIONS
	public int boardWidth = 30;
	public int boardHeight = 30;
	GridLayout borderLayout = new GridLayout(31, 30);
	FlowLayout menuLayout = new FlowLayout();
	Dimension adminDim = new Dimension(100, 25);
	Font font = new Font("Arial", Font.BOLD, 12);
	
	//BOARD BUTTONS
	public JButton startButton;
	public JButton [] topBorder;
	public static GameButton[][] board;
	
	public GamePanel () {
		startButtonSetup();
		
		currentState = menuState;
		
	}
	
	public void draw () {
		if (currentState == gameState) {
			drawGame();
		} else if (currentState == endState) {
			drawEnd();
		} else {
			drawMenu();
		}
	}
	
	public void updateState () {
		currentState++;
		if (currentState >= 3) {
			currentState = 0;
		}
	}
	
	public void drawMenu () {
		setBackground(Color.WHITE);
		
		startButton.setText("Start?");
		
		PenteGame.gameFrame.pack();
	}
	
	public void drawGame () {
		setLayout(borderLayout);
		startButton.setText("Exit");
		setBackground(Color.GRAY);
		
		isPlayerOne = true;
		topBorderSetup();
		boardSetup();
		
		PenteGame.gameFrame.pack();
	}
	
	public void drawEnd () {
		removeBoard();
		setLayout(menuLayout);
		setBackground(Color.BLACK);
		
		startButton.setText("Menu");
		
		PenteGame.gameFrame.pack();
	}
	
	private void removeBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				remove(board[row][col]);
			}
		}
		for (int x = 0; x < topBorder.length; x++) {
			remove(topBorder[x]);
		}
	}
	
	private void startButtonSetup() {
		startButton = new JButton("Start!");
		startButton.setFont(font);
		startButton.addActionListener(this);
		startButton.setBackground(admin);
		startButton.setSize(adminDim);
		add(startButton);
	}
	
	private void topBorderSetup () {
		topBorder = new JButton[29];
		for (int x = 0; x < topBorder.length; x++) {
			topBorder[x] = new JButton();
			topBorder[x].setBorderPainted(false);
			topBorder[x].setBackground(border);
			topBorder[x].setPreferredSize(adminDim);
			add(topBorder[x]);
		}
	}
	
	private void boardSetup() {
		board = new GameButton[boardHeight][boardWidth];
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
			draw();
		}
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (!board[row][col].isLocked() && buttonPressed == board[row][col]) {
					if (isPlayerOne) {
						board[row][col].setP1();
						board[row][col].checkCapture(row, col, isPlayerOne);
						isPlayerOne = false;
						System.out.println(GameButton.p1Captures);
					} else {
						board[row][col].setP2();
						board[row][col].checkCapture(row, col, isPlayerOne);
						isPlayerOne = true;
						System.out.println(GameButton.p2Captures);
					}
				}
			}
		}
	}
}
