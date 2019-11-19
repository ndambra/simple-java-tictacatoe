
/*-------------------------------------------------------------
| Copyright (c) 2019 Northrop Grumman Systems Corporation.
| All rights reserved. This software was developed under U.S.
| Government Contract No. HHSN316201200036W HC104719F0002, and
| therefore the U.S. Government is granted a copyright
| license to this software for U.S. Government purposes.
+-------------------------------------------------------------*/

public class GameState {
	private final int PLAYER_ONE = 1;
	private final int PLAYER_TWO = 2;
	private int turnCount;
	private boolean gameOver;
	private int playersTurn;
	private char[][] gameGrid;
	private final int gameSize = 3;

	public GameState() {
		this.turnCount = 0;
		this.playersTurn = 1;
		this.gameOver = false;
		this.gameGrid = new char[gameSize][gameSize];
	}

	public int getTurn() {
		return this.playersTurn;
	}

	public int getTurnCount() {
		return this.turnCount;
	}

	public boolean isGameOver() {
		return this.gameOver;
	}

	public void setGameState() {
		this.gameOver = !this.gameOver;
	}

	public void nextPlayersTurn() {
		if (this.playersTurn == 1)
			this.playersTurn = 2;
		else
			this.playersTurn = 1;
	}

	public void resetGrid() {
		System.out.println("Let's reset the grid");
		for (int row = 0; row < gameSize; row++) {
			for (int col = 0; col < gameSize; col++) {
				this.gameGrid[row][col] = '-';
			}
		}
	}

	public void printGrid() {
		for (int row = 0; row < gameSize; row++) {
			for (int col = 0; col < gameSize; col++) {
				System.out.print(this.gameGrid[row][col] + " ");
			}
			System.out.println();
		}
	}

	public void markSpot(int player, int row, int col) {

		if (player == 1)
			this.gameGrid[row][col] = 'X';
		else
			this.gameGrid[row][col] = 'O';

		this.turnCount++;
	}

	public boolean spotOccupied(int row, int col) {
		if (this.gameGrid[row][col] == '-')
			return false;
		return true;
	}

	public boolean checkforWin(int row, int col) {
		//there's probably a better way but this is fine for now
		if(this.turnCount < 4) 
			return false;
		
		int win = 0;
		char currPlay;
		if (this.playersTurn == PLAYER_ONE)
			currPlay = 'X';
		else
			currPlay = 'O';

		// row win
		for (int i = 0; i < gameSize; i++) {
			if (this.gameGrid[row][i] == currPlay) {
				win++;
			} else {
				win = 0;
				break;
			}
		}

		if (win == 3)
			return true;

		// column win
		for (int i = 0; i < gameSize; i++) {
			if (this.gameGrid[i][col] == currPlay) {
				win++;
			} else {
				win = 0;
				break;
			}
		}

		// diagonal win
		if (col == row) {
			for (int i = 0; i < gameSize; i++) {
				if (this.gameGrid[i][i] == currPlay) {
					win++;
				} else {
					win = 0;
					break;
				}
			}
		}

		if (win == 3)
			return true;

		// anti diagonal win
		if (col + row == gameSize - 1) {
			for (int i = 0; i < gameSize; i++) {
				if (this.gameGrid[i][(gameSize-1)-i] == currPlay) {
					win++;
				} else {
					win = 0;
					break;
				}
			}
		}

		if (win == 3)
			return true;
		else
			return false;
	}
}
