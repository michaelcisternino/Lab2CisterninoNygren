package main;
/** @Author Michael Cisternino, EJ Nygren
 * class Card: for creating playing card objects
 *
 */
import java.util.Comparator;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eRank;
import pokerEnums.eSuit;

public final class Card {
	@XmlElement
	private eSuit Suit;
	@XmlElement
	private eRank Rank;
	@XmlElement
	private boolean Wild = false;
	@XmlElement
	private String CardImg;
	
	private Card()
	{
	}
	
	/**
	 * Create a new card of a given rank and suit.
	 * @param suit
	 * @param rank
	 */
	public Card(eSuit suitVal, eRank cardRank) {
		Suit = suitVal;
		Rank = cardRank;
		this.Wild = false;
	}
	
	public Card(eSuit suit, eRank rank, boolean Wild) {
		Suit = suit;
		Rank = rank;
		this.Wild = Wild;
	}
	
	/**
	 * Getter for Rank
	 * @return
	 */
	public eRank getRank() {
		return this.Rank;
	}

	/**
	 * Getter for Suit
	 * @return
	 */
	public eSuit getSuit() {
		return this.Suit;
	}
	
	public boolean getWild()
	{
		return this.Wild;
	}
	
	public void setWild()
	{
		this.Wild = true;
	}
	
	public String getCardImg()
	{
		return this.CardImg;
	}

	/**
	 * CardRank Comparator is used for sorting the collection by rank
	 */
	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.getRank().getRank();
		   int Cno2 = c2.getRank().getRank();

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }};


}
