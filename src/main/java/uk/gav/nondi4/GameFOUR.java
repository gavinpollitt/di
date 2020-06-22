package uk.gav.nondi4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import uk.gav.nondi4.interfaces.DiceFactory;
import uk.gav.nondi4.interfaces.GameResultProcessor;
import uk.gav.nondi4.stats.AverageThrowStat;
import uk.gav.nondi4.stats.FaceCountStat;
import uk.gav.nondi4.stats.StatProducer;

public class GameFOUR {
	public static void main(String[] args) {		
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));
		new GameFOUR().play(players);
	}
	
	public void play(final Players players) {
		
		DiceFactory df = new BasicDiceFactory(6, 4);
		GameResultProcessor rp = new HighestRollResultProcessor();
		List<StatProducer> sp = Arrays.asList(new AverageThrowStat(), new FaceCountStat());
		HashSet<StatProducer> statProducers = new HashSet<>(sp);

		Game g = new Game(df,players,rp,statProducers);
		System.out.println(g.play());
		System.out.println(g.play());
		System.out.println(g.getGameScores());
		
		df = new BasicDiceFactory(6, 5);
		rp = new DoubleUpResultProcessor();
		g = new Game(df, players,rp,statProducers);
		System.out.println(g.play());
		System.out.println(g.getGameScores());

	}
}
