package uk.gav.nondi5;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import uk.gav.nondi5.interfaces.DiceFactory;
import uk.gav.nondi5.interfaces.GameResultProcessor;
import uk.gav.nondi5.stats.FaceCountStat;
import uk.gav.nondi5.stats.StatProducer;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighestTest
{
	private final static List<String> pList = Arrays.asList("Bob","Todd");
	private static Set<StatProducer> statProducers;
	private static GameResultProcessor resultProcessor;
	private static DiceFactory diceFactory;
	
	private Game game;
	
	@BeforeClass
	public static void init() {
		List<StatProducer> sp = Arrays.asList(new FaceCountStat());
		statProducers = new HashSet<>(sp);
		resultProcessor = new HighestRollResultProcessor();
		diceFactory = new TestDiceFactory(12, 2);
	}

	@Before
	public void initialiseGame() {
		Players players = new Players(new HashSet<>(pList));
		GameData gd = new GameData(diceFactory, players);
		game = new Game(gd, resultProcessor, statProducers);
	}
	
	
	@Test   
	public void play1() {
		final String res = "Bob with 19 beats Todd with 8";
        String result = game.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		String res = "Todd with 23 beats Bob with 8";
        String result = game.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
        
        result = game.play();
        res = "Both players rolled: 19. It's a draw";
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
        
        result = game.getGameScores();
        res = "Bob won 0 gamesTodd won 1 game";
        assertTrue("Output should be '" + res + "', but was " + result, result.replaceAll("\\n", "").equals(res));
	}	
}
