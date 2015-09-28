package pokerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import main.Card;
import main.Hand;
import pokerEnums.eSuit;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;



public class HandTest {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestFourOfAKind() {
		ArrayList<Card> FourKind = new ArrayList <Card>();
		FourKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		FourKind.add(new Card(eSuit.HEARTS, eRank.ACE));
		FourKind.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		FourKind.add(new Card(eSuit.SPADES, eRank.ACE));
		FourKind.add(new Card(eSuit.CLUBS, eRank.FIVE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(FourKind);
		h.EvalHand();
		assertTrue(eHandStrength.FourOfAKind.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestOnePair() {
		ArrayList<Card> Pair = new ArrayList <Card>();
		Pair.add(new Card(eSuit.CLUBS, eRank.ACE));
		Pair.add(new Card(eSuit.HEARTS, eRank.ACE));
		Pair.add(new Card(eSuit.DIAMONDS, eRank.TWO));
		Pair.add(new Card(eSuit.SPADES, eRank.SIX));
		Pair.add(new Card(eSuit.CLUBS, eRank.FIVE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(Pair);
		h.EvalHand();
		assertTrue(eHandStrength.Pair.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestTwoPair() {
		ArrayList<Card> TwoPair = new ArrayList <Card>();
		TwoPair.add(new Card(eSuit.CLUBS, eRank.ACE));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.ACE));
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		TwoPair.add(new Card(eSuit.SPADES, eRank.KING));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.FIVE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(TwoPair);
		h.EvalHand();
		assertTrue(eHandStrength.TwoPair.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestHighCard() {
		ArrayList<Card> HighCard = new ArrayList <Card>();
		HighCard.add(new Card(eSuit.CLUBS, eRank.ACE));
		HighCard.add(new Card(eSuit.HEARTS, eRank.SEVEN));
		HighCard.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		HighCard.add(new Card(eSuit.SPADES, eRank.EIGHT));
		HighCard.add(new Card(eSuit.CLUBS, eRank.JACK));
		
		Hand h = new Hand();
		h = Hand.EvalHand(HighCard);
		h.EvalHand();
		assertTrue(eHandStrength.HighCard.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestThreeOfAKind() {
		ArrayList<Card> ThreeKind = new ArrayList <Card>();
		ThreeKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		ThreeKind.add(new Card(eSuit.HEARTS, eRank.FOUR));
		ThreeKind.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		ThreeKind.add(new Card(eSuit.SPADES, eRank.TEN));
		ThreeKind.add(new Card(eSuit.CLUBS, eRank.ACE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(ThreeKind);
		h.EvalHand();
		assertTrue(eHandStrength.ThreeOfAKind.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestStraight() {
		ArrayList<Card> Straight = new ArrayList <Card>();
		Straight.add(new Card(eSuit.CLUBS, eRank.FOUR));
		Straight.add(new Card(eSuit.HEARTS, eRank.SEVEN));
		Straight.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		Straight.add(new Card(eSuit.SPADES, eRank.SIX));
		Straight.add(new Card(eSuit.CLUBS, eRank.THREE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(Straight);
		h.EvalHand();
		assertTrue(eHandStrength.Straight.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestFlush() {
		ArrayList<Card> Flush = new ArrayList <Card>();
		Flush.add(new Card(eSuit.HEARTS, eRank.THREE));
		Flush.add(new Card(eSuit.HEARTS, eRank.SEVEN));
		Flush.add(new Card(eSuit.HEARTS, eRank.JACK));
		Flush.add(new Card(eSuit.HEARTS, eRank.QUEEN));
		Flush.add(new Card(eSuit.HEARTS, eRank.FIVE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(Flush);
		h.EvalHand();
		assertTrue(eHandStrength.Flush.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestFullHouse() {
		ArrayList<Card> FullHouse = new ArrayList <Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		FullHouse.add(new Card(eSuit.SPADES, eRank.KING));
		FullHouse.add(new Card(eSuit.CLUBS, eRank.KING));
		
		Hand h = new Hand();
		h = Hand.EvalHand(FullHouse);
		h.EvalHand();
		assertTrue(eHandStrength.FullHouse.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestStraightFlush() {
		ArrayList<Card> StraightFlush = new ArrayList <Card>();
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.NINE));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.TEN));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.JACK));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		
		Hand h = new Hand();
		h = Hand.EvalHand(StraightFlush);
		h.EvalHand();
		assertTrue(eHandStrength.StraightFlush.getHandStrength() == h.getHandStrength());
	}
	
	@Test
	public void TestRoyalFlush() {
		ArrayList<Card> RoyalFlush = new ArrayList <Card>();
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.TEN));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.JACK));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.QUEEN));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.KING));
		RoyalFlush.add(new Card(eSuit.HEARTS, eRank.ACE));
		
		Hand h = new Hand();
		h = Hand.EvalHand(RoyalFlush);
		h.EvalHand();
		assertTrue(eHandStrength.RoyalFlush.getHandStrength() == h.getHandStrength());
	}

}

