package main;
//@Author Michael Cisternino, EJ Nygren
import java.util.ArrayList;
import java.util.Collections;
import main.Hand;
/**
 * Class GamePlay initiates a "game" by creating an array list of hands representing players and then evaluates
 * the given hands.
 */
public class GamePlay {
	
	private int NmbrPlayers = 3;
	private ArrayList<Hand> Players = new ArrayList<Hand>(4);
	private Deck newDeck = new Deck();
	
	public void Gameplay() {
	for (int i = 0; i <= NmbrPlayers; i++) {
		Hand hand = new Hand(newDeck);
		hand.EvalHand();
		Players.add(i, hand);
	}
	Collections.sort(Players, Hand.HandRank);
	}
	// returns the hand of the winning player
	public String getWinnerHand() {
		return Players.get(0).geteHand().toString();
	}
}
