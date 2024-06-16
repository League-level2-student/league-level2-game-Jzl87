package Pente;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class GameButton extends JButton {

	// MODERATION
	public static int p1Captures = 0;
	public static int p2Captures = 0;
	public GameButton[][] board = GamePanel.board;

	// COLORS
	public static final Color p1Color = Color.RED;
	public static final Color p2Color = Color.BLUE;
	public static final Color openSpace = Color.WHITE;

	// CONSTRUCTOR VAR
	Color buttonColor;

	public GameButton(Color buttonColor) {
		this.buttonColor = buttonColor;
		setBackground(buttonColor);
	}

	public boolean isLocked() {
		if (buttonColor.equals(openSpace)) {
			return false;
		}
		return true;
	}

	public void setP1() {
		buttonColor = p1Color;
		setBackground(buttonColor);
	}

	public void setP2() {
		buttonColor = p2Color;
		setBackground(buttonColor);
	}

	public void checkCapture(int row, int col, boolean p1) {
		captureDirection(row, col, 1, 0, p1); // UP
		captureDirection(row, col, -1, 0, p1); // DOWN
		captureDirection(row, col, 0, -1, p1); // LEFT
		captureDirection(row, col, 0, 1, p1); // RIGHT
		captureDirection(row, col, 1, 1, p1); // UP/LEFT
		captureDirection(row, col, 1, -1, p1); // UP/RIGHT
		captureDirection(row, col, -1, 1, p1); // DOWN/LEFT
		captureDirection(row, col, -1, -1, p1); // DOWN/RIGHT

	}

	public boolean checkFive(int row, int col, int playerColor) {
		return checkRowFor5(row, playerColor) || checkColFor5(col, playerColor) || checkD1For5(row, col, playerColor)
				|| checkD2For5(row, col, playerColor);
	}

	// /
	private boolean checkD2For5(int row, int col, int playerColor) {

		int r;
		int c;

		if (row + col <= 29) {
			r = row + col;
			c = 0;
		} else {
			r = 29;
			c = (row + col) - 29;
		}

		int count = 0;
		for (int i = 0; i < GamePanel.boardDim; i++) {
			if (r - i >= 0 && c + i < GamePanel.boardDim && board[r - i][c + i].isColor() == playerColor) {
				count++;
			} else {
				count = 0;
			}

			if (count >= 5) {
				return true;
			}
		}
		return false;
	}

	// \
	private boolean checkD1For5(int row, int col, int playerColor) {
		int r;
		int c;

		if (row >= col) {
			r = row - col;
			c = 0;
		} else {
			r = 0;
			c = col - row;
		}

		int count = 0;
		for (int x = 0; x < GamePanel.boardDim; x++) {
			if (r + x < GamePanel.boardDim && c + x < GamePanel.boardDim
					&& board[r + x][c + x].isColor() == playerColor) {
				count++;
			} else {
				count = 0;
			}

			if (count >= 5) {
				return true;
			}
		}
		return false;

	}
	
	public static void resetCaptures() {
		p1Captures = 0;
		p2Captures = 0;
	}
	
	private boolean checkRowFor5(int row, int playerColor) {
		int count = 0;
		for (int c = 0; c < GamePanel.boardDim; c++) {
			if (board[row][c].isColor() == playerColor) {
				count++;
			} else {
				count = 0;
			}

			if (count >= 5) {
				return true;
			}
		}
		return false;
	}

	private boolean checkColFor5(int col, int playerColor) {
		int count = 0;
		for (int r = 0; r < GamePanel.boardDim; r++) {
			if (board[r][col].isColor() == playerColor) {
				count++;
			} else {
				count = 0;
			}

			if (count >= 5) {
				return true;
			}
		}
		return false;
	}

	private void captureDirection(int row, int col, int ver, int hor, boolean p1) {
		if (p1) {
			if (borderCheck(row + ver, col + hor) && board[row + ver][col + hor].isColor() == 2) {
				if (borderCheck(row + ver * 2, col + hor * 2) && board[row + ver * 2][col + hor * 2].isColor() == 2) {
					if (borderCheck(row + ver * 3, col + hor * 3)
							&& board[row + ver * 3][col + hor * 3].isColor() == 1) {
						board[row + ver][col + hor].resetButton();
						board[row + ver * 2][col + hor * 2].resetButton();
						p1Captures++;
						System.out.println("p1c");
					}
				}
			}
		} else {
			if (borderCheck(row + ver, col + hor) && board[row + ver][col + hor].isColor() == 1) {
				if (borderCheck(row + ver * 2, col + hor * 2) && board[row + ver * 2][col + hor * 2].isColor() == 1) {
					if (borderCheck(row + ver * 3, col + hor * 3)
							&& board[row + ver * 3][col + hor * 3].isColor() == 2) {
						board[row + ver][col + hor].resetButton();
						board[row + ver * 2][col + hor * 2].resetButton();
						p2Captures++;
						System.out.println("p2c");
					}
				}
			}
		}
	}

	private boolean borderCheck(int row, int col) {
		return row < board.length && col < board[0].length && row > -1 && col > -1;
	}

	private int isColor() {

		if (buttonColor.equals(p1Color)) {
			return 1;
		} else if (buttonColor.equals(p2Color)) {
			return 2;
		} else if (buttonColor.equals(openSpace)) {
			return 0;
		}

		return -1;
	}

	private void resetButton() {
		buttonColor = openSpace;
		setBackground(buttonColor);
	}
}
