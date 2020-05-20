package uk.gav.nondiTWO;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighestTest
{
	private static DiceFactory diceFactory;
	
	@BeforeClass
	public static void init() {
		diceFactory = new TestDiceFactory(12,2);
		
	}
	
	@Test   
	public void play1() {
		final String res = "Player 1 with 19 beats player 2 with 8";
        HighestRollGame g = new HighestRollGame(diceFactory);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Player 2 with 23 beats player 1 with 8";
        HighestRollGame g = new HighestRollGame(diceFactory);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
}
