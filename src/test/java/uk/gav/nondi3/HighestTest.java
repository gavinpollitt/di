package uk.gav.nondi3;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import uk.gav.nondi3.interfaces.DiceFactory;
import uk.gav.nondi3.interfaces.GameResultProcessor;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighestTest
{
	private static DiceFactory diceFactory;
	private static Players players;
	
	@BeforeClass
	public static void init() {
		diceFactory = new TestDiceFactory(12,2);
		List<String> pList = Arrays.asList("Bob","Todd");
		players = new Players(new HashSet<>(pList));
		
	}
	
	@Test   
	public void play1() {
		final String res = "Bob with 19 beats Todd with 8";
		GameResultProcessor resultProcessor = new HighestRollResultProcessor();
        Game g = new Game(diceFactory, players, resultProcessor);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Todd with 23 beats Bob with 8";
		GameResultProcessor resultProcessor = new HighestRollResultProcessor();
        Game g = new Game(diceFactory, players, resultProcessor);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
}
