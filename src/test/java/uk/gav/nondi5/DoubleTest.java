package uk.gav.nondi5;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.gav.nondi5.interfaces.DiceFactory;
import uk.gav.nondi5.interfaces.GameResultProcessor;
import uk.gav.nondi5.stats.AverageThrowStat;
import uk.gav.nondi5.stats.StatProducer;


public class DoubleTest
{
	private final static List<String> pList = Arrays.asList("Bob","Todd");
	private static Set<StatProducer> statProducers;
	private static GameResultProcessor resultProcessor;
	private static DiceFactory diceFactory;
	
	private Game game;
	
	@BeforeClass
	public static void init() {
		List<StatProducer> sp = Arrays.asList(new AverageThrowStat());
		statProducers = new HashSet<>(sp);
		resultProcessor = new DoubleUpResultProcessor();		
		diceFactory = new TestDiceFactory(6, 3);
	}

	@Before
	public void initialiseGame() {
		Players players = new Players(new HashSet<>(pList));
		GameData gd = new GameData(diceFactory, players);
		game = new Game(gd, resultProcessor, statProducers);
	}
	
	@Test   
	public void play1() {
		final String res = "Bob rolled no doubles giving score 12 beats Todd who rolled 2X1 = 4 giving score 10";
        String result = game.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Todd rolled 3X2 = 18 giving score 18 beats Bob who rolled no doubles giving score 8";
        String result = game.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play3() {
		final String res = "Both players rolled: 11. It's a draw";
         String result = game.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
}
