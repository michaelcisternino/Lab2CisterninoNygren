package main;
// @Author Michael Cisternino, EJ Nygren
import java.util.ArrayList;
import java.util.Collections;

import java.io.StringWriter;
//not used yet
// import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pokerEnums.eRank;
import pokerEnums.eSuit;

@XmlRootElement
public class Deck {
	
	@XmlElement (name="Remaining Card")
	private ArrayList<Card> deck;
	
	
	public Deck() {
		
		// Create an ArrayList of Cars, add each card
		ArrayList<Card> MakingDeck = new ArrayList<Card>();
		for(short i = 0; i <= 3; i++) 
		{
			eSuit SuitValue= eSuit.values()[i];
			for (short y = 0; y <= 12; y++)
			{
				eRank RankValue = eRank.values()[y];
				Card NewCard = new Card(SuitValue, RankValue);
				MakingDeck.add(NewCard);
			}
			}
		// set the instance variable
		deck = MakingDeck;
		Shuffle();
	}
	/**
	 * this code isn't used yet
	 * @param NbrOfJokers
	public Deck(int NbrOfJokers) {

		this();
		
		for (short i = 1; i <= NbrOfJokers; i++) {
			cards.add(new Card(eSuit.JOKER,eRank.JOKER,53));
		}
		Shuffle();
	}
	
	
	public Deck(int NbrOfJokers, ArrayList<Card> WildCards) {

		this(NbrOfJokers);
		
		
		for (Card deckCard : cards)
		{
			for (Card WildCard: WildCards)
			{
				if ((deckCard.getSuit() == WildCard.getSuit()) &&
						(deckCard.getRank() == WildCard.getRank()))
						{
							deckCard.setWild();
						}					
			}
		}
		Shuffle();
	}
	*/
	
	private void Shuffle() {
		// shuffle the cards
		Collections.shuffle(deck);
		}
		
	public Card drawCard() 
	{
		// Remove the first card from the deck and return the card
		Card FirstCard = deck.get(0);
		deck.remove(0);
		return FirstCard;
	}
	
	public int deckSize()
	{	
		// returns the total number of cards still in thed eck
		return deck.size();
	}
	
	public ArrayList<Card> getCards()
	{
		return this.deck;
	}
	
	public StringWriter SerializeMe()
	{
	    StringWriter sw = new StringWriter();
		try
		{
		    //Write it
		    JAXBContext ctx = JAXBContext.newInstance(Deck.class);
		    Marshaller m = ctx.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(this, sw);
		    sw.close();			
		}
		catch (Exception ex)
		{
			
		}
    
    return sw;
	}
	
}
