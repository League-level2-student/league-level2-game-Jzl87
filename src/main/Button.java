package main;

import java.awt.Color;

import javax.swing.JButton;

public class Button extends JButton {

	private static final long serialVersionUID = 1L;

	final static int up = -30;
	final static int down = 30;
	final static int left = -1;
	final static int right = 1;

	// constructor variables
	private Color buttonColor;
	public int buttonID;
	public boolean buttonLocked;
	public int buttonSize;
	
	private static final Color black = Color.BLACK;
	

	// BUTTON CREATION CONSTRUCTOR
	public Button(boolean buttonLocked, Color buttonColor) {
		this.buttonLocked = buttonLocked;
		this.buttonColor = buttonColor;
		buttonSize = 30;
		setSize(buttonSize, buttonSize);

		@SuppressWarnings("unused")
		JButton button = new JButton();
		setBackground(buttonColor);
	}

	// setters and getters
	public void setButtonLocked(boolean setLock) {
		buttonLocked = setLock;
	}

	public void setButtonColor(Color setColor) {
		buttonColor = setColor;
		setBackground(buttonColor);
	}

	public void setButtonID(int setID) {
		buttonID = setID;
	}

	public int getButtonID() {
		return buttonID;
	}

	public Color getColor() {
		return buttonColor;
	}

	public boolean getButtonLocked() {
		return buttonLocked;
	}

	/*
	 *
	 * BELOW CHECKS CAPTURES
	 * 
	 * 
	 */

	public static boolean checkRed(int currentButtonID) {
		if (currentButtonID > 900) {
			return false;
		} else {
		return Panel.game.getPlayableButton(currentButtonID).getColor() == Panel.red;
		}
	}

	public static boolean checkBlue(int currentButtonID) {
		if (currentButtonID > 900) {
			return false;
		} else {
		return Panel.game.getPlayableButton(currentButtonID).getColor() == Panel.blue;
		}
	}
	
	// main capture method
	public boolean checkCapture(int CBID, boolean isPlayerOne, int direction) {

		if (isPlayerOne) {
			if (checkBlue(CBID + direction) && checkBlue(CBID + direction * 2) && checkRed(CBID + direction * 3)) {
				return true;
			}
			return false;

		} else {
			if (checkRed(CBID + direction) && checkRed(CBID + direction * 2) && checkBlue(CBID + direction * 3)) {
				return true;
			}
			return false;
		}
	}

	public void resetButtonCapture(int CBID, int direction) {
		Panel.game.getPlayableButton(CBID + direction).setButtonLocked(false);
		Panel.game.getPlayableButton(CBID + direction * 2).setButtonLocked(false);

		Panel.game.getPlayableButton(CBID + direction).setButtonColor(black);
		Panel.game.getPlayableButton(CBID + direction * 2).setButtonColor(black);

	}

	public void addCapture(boolean isPlayerOne) {
		if (isPlayerOne) {
			Panel.captureCounter1++;
		} else {
			Panel.captureCounter2++;
		}
	}

	public int doCapture(int CBID, boolean isPlayerOne) {

		int captureCounter = 0;
		if (checkCapture(CBID, isPlayerOne, up)) {

			resetButtonCapture(CBID, up);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, down)) {

			resetButtonCapture(CBID, down);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, left)) {

			resetButtonCapture(CBID, left);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, right)) {

			resetButtonCapture(CBID, right);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, up + left)) {

			resetButtonCapture(CBID, up + left);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, up + right)) {

			resetButtonCapture(CBID, up + right);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, down + left)) {

			resetButtonCapture(CBID, down + left);
			addCapture(isPlayerOne);
			captureCounter++;
		}

		if (checkCapture(CBID, isPlayerOne, down + right)) {

			resetButtonCapture(CBID, down + right);
			addCapture(isPlayerOne);
			captureCounter++;
		}
		return captureCounter;
	}

	/*
	 * 
	 * 
	 * 5 IN A ROW CHECK
	 * 
	 * 
	 */

	public static int checkDirection(int direction, int CBID, boolean isPlayerOne) {

		int redCounter = 0;
		int blueCounter = 0;

		if (isPlayerOne) {
			for (int x = 1; x < 5; x++) {
				if (checkRed(CBID + direction * x) ) {
					
					redCounter++;
				}
			}	
			return redCounter;
			
		} else {
			for (int x = 1; x < 5; x++) {
				if (checkBlue(CBID + direction * x)) {
					blueCounter++;
				}
			}
			return blueCounter;
		}

	}

	public static int checkPente(int CBID, boolean isPlayerOne) {
		int redCounter = 1;
		int blueCounter = 1;

		if (isPlayerOne) {
			
			redCounter += checkDirection(up, CBID, isPlayerOne) +
			checkDirection(down, CBID, isPlayerOne) +
			checkDirection(left, CBID, isPlayerOne) +
			checkDirection(right, CBID, isPlayerOne)+ 
			
			checkDirection(up + left, CBID, isPlayerOne) +
			checkDirection(up + right, CBID, isPlayerOne)+ 
			checkDirection(down + left, CBID, isPlayerOne)+ 
			checkDirection(down + right, CBID, isPlayerOne); 	
			
			return redCounter;
			
		} else {
			
			blueCounter += checkDirection(up, CBID, isPlayerOne) +
			checkDirection(down, CBID, isPlayerOne) +
			checkDirection(left, CBID, isPlayerOne) +
			checkDirection(right, CBID, isPlayerOne)+ 
					
			checkDirection(up + left, CBID, isPlayerOne) +
			checkDirection(up + right, CBID, isPlayerOne)+ 
			checkDirection(down + left, CBID, isPlayerOne)+ 
			checkDirection(down + right, CBID, isPlayerOne);
			
			return blueCounter;
		}
	}
	
	public int checkFiveRow(int CBID, boolean isPlayerOne) {
		int winMarker = 0;
		// result = 0: means not pent
		// result = 1: means player 1 pent
		// result = 2 :means player 2 pent
		
		if (isPlayerOne && checkPente(CBID, isPlayerOne) >= 5) {
			winMarker = 1;
		} else if (!isPlayerOne && checkPente(CBID, isPlayerOne) >= 5) {
			winMarker = 2;
		} 
		return winMarker;
	}

	public void addActionListener(GameWindow gameWindow) {
	
		
	}
}
