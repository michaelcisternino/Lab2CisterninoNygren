package main;
//@Author Michael Cisternino, Eric Nygren
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;
/** 
 * class Hand represents a hand dealt from a given deck by using an array list of type Card.
 * 
 */
public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;

	@XmlElement
	private eHandStrength HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;
	@XmlElement
	private int Kicker;

	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	// dJoker is not used yet
	// private static Deck dJoker = new Deck();

	public Hand() {

	}

	public void AddCardToHand(Card c) {
		if (this.CardsInHand == null) {
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}

	public Card GetCardFromHand(int location) {
		return CardsInHand.get(location);
	}

	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawCard());
		}
		CardsInHand = Import;

	}

	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID) {
		this.playerID = playerID;
	}

	public UUID getPlayerID() {
		return playerID;
	}

	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength.getHandStrength();
	}
	
	public eHandStrength geteHand() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {

		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// five of a Kind

		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
				.getRank()) {
			ScoreHand(eHandStrength.FiveOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					0);
		}

		// Straight Evaluation
		else if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluate Royal Flush
		if (Straight == true && Flush == true && CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					0, 0);
		}
		// Four of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand
				.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0);
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), 0);
		}
		
		// Full House
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand
				.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0);
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()
				&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0);
		}
		// Flush
		else if (Flush) {
			ScoreHand(eHandStrength.Flush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0);
		}

		// Straight
		else if (Straight) {
			ScoreHand(eHandStrength.Straight, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0);
		}

		// Three of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
		}

		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() &&
				CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() &&
				CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() &&
				CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
		}

		// Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
		}
		else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank() == 
				CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
					ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
							CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 
								CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
		}

		// High Card
		// I'll give you this one :)
		else {
			ScoreHand(eHandStrength.HighCard, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0,
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());
		}
	}

	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST;
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getKicker() - h1.getKicker();
			if (result != 0) {
				return result;
			}

			return 0;
		}
	};
}