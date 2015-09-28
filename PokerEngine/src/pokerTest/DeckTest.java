package pokerTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import main.Deck;


public class DeckTest {
	
	private static Deck d;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		d = new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestFullDeck() {

		int iExpectedSize = 52;
		assertTrue(d.deckSize() == iExpectedSize);
		
		int iRemoveCard = 51;
		d.drawCard();
		assertTrue(d.deckSize() == iRemoveCard);
	}

}
