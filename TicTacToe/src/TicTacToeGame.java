
/*-------------------------------------------------------------
| Copyright (c) 2019 Northrop Grumman Systems Corporation.
| All rights reserved. This software was developed under U.S.
| Government Contract No. HHSN316201200036W HC104719F0002, and
| therefore the U.S. Government is granted a copyright
| license to this software for U.S. Government purposes.
+-------------------------------------------------------------*/
import java.util.Scanner;

public class TicTacToeGame {
	static Scanner input = new Scanner(System.in);
	static GameState myGame;

	public static void main(String[] args) {
		myGame = new GameState();
		playGame();
		System.out.println("Thanks for playing!");
	}

	private static void playGame() {
		System.out.println("Let's Go!");
		System.out.println("Player 1 vs. Player 2");
		myGame.resetGrid();
		myGame.printGrid();

		while (!(myGame.isGameOver())) {
			if (myGame.getTurnCount() < 9) {
				playersTurn();
			} else {
				myGame.setGameState();
				System.out.println("Stalemate! It's a Tie!");
			}

		}
	}

	private static void playersTurn() {
		boolean valid = false;
		int col = 0;
		int row = 0;
		while(!valid){
			System.out.println("Pick a spot (row): ");
			row = input.nextInt();
			System.out.println("Pick a spot (col): ");
			col = input.nextInt();
			if ((row >= 0 && row <= 2) && (col >= 0 && col <= 2)) {
				if (!(myGame.spotOccupied(row, col))) {
					valid = true;
				} else
					System.out.println("Invalid choice, try again");
			} else
				System.out.println("Invalid choice, try again");
		}
			
		myGame.markSpot(myGame.getTurn(), row, col);
		myGame.printGrid();
		isThereAWinner(row,col);
	}

	private static void isThereAWinner(int row, int col) {
		boolean winner = myGame.checkforWin(row, col);
		
		if (winner) {
			myGame.setGameState();
			printResult(myGame.getTurn());
		} else
			myGame.nextPlayersTurn();
	}

	private static void printResult(int player) {
		System.out.println("Player " + player + " wins!!");

	
	}

}
