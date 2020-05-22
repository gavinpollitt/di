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
public class DoubleTest
{
	private static DiceFactory diceFactory;
	private static Players players;
	
	@BeforeClass
	public static void init() {
		diceFactory = new TestDiceFactory(6,3);
		List<String> pList = Arrays.asList("Bob","Todd");
		players = new Players(new HashSet<>(pList));
		
	}
	
	@Test   
	public void play1() {
		final String res = "Bob rolled no doubles giving score 12 beats Todd who rolled 2X1 = 4 giving score 10";
		GameResultProcessor resultProcessor = new DoubleUpResultProcessor();
        Game g = new Game(diceFactory, players, resultProcessor);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Todd rolled 3X2 = 18 giving score 18 beats Bob who rolled no doubles giving score 8";
		GameResultProcessor resultProcessor = new DoubleUpResultProcessor();
        Game g = new Game(diceFactory, players, resultProcessor);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play3() {
		final String res = "Both players rolled: 11. It's a draw";
		GameResultProcessor resultProcessor = new DoubleUpResultProcessor();
        Game g = new Game(diceFactory, players, resultProcessor);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
}
