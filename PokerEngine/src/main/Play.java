package main;
//@Author Michael Cisternino, EJ Nygren
import main.GamePlay;

public class Play {

	public static void main(String[] args) {
		// created this class to test MyInteger class and methods

		GamePlay newGame = new GamePlay();
		newGame.Gameplay();
		System.out.println("Winner with a " +newGame.getWinnerHand());

	}
}