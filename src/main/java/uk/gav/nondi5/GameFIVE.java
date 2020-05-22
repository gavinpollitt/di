package uk.gav.nondi5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import uk.gav.nondi5.interfaces.DiceFactory;
import uk.gav.nondi5.interfaces.GameResultProcessor;
import uk.gav.nondi5.stats.AverageThrowStat;
import uk.gav.nondi5.stats.FaceCountStat;
import uk.gav.nondi5.stats.StatProducer;

public class GameFIVE {
	public static void main(String[] args) {				
		//Singletons
		GameResultProcessor hrp = new HighestRollResultProcessor();
		GameResultProcessor dup = new DoubleUpResultProcessor();

		List<StatProducer> sp = Arrays.asList(new AverageThrowStat(), new FaceCountStat());
		HashSet<StatProducer> statProducers = new HashSet<>(sp);

		//Game-specific
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));
		DiceFactory df = new BasicDiceFactory(12, 3);
		GameData gd = new GameData(df, players);

    	sep();
    	System.out.println("Game One");
		Game g = new Game(gd,hrp,statProducers);
        System.out.println(g.play());
        System.out.println(g.play());
        System.out.println(g.play());
        System.out.println(g.getGameScores());
        
    	sep();
    	System.out.println("Game Two");
		players = new Players(new HashSet<>(pList));
		gd = new GameData(df, players);
		g = new Game(gd,hrp,statProducers);
        System.out.println(g.play());
        System.out.println(g.getGameScores());

    	sep();
    	System.out.println("Game Three");
		pList = Arrays.asList("Tracy","Sharon");
		players = new Players(new HashSet<>(pList));
		df = new BasicDiceFactory(6, 5);
		gd = new GameData(df, players); // Comment out to show still works, but wrong data
		g = new Game(gd,dup,statProducers);
		System.out.println(g.play());
		System.out.println(g.getGameScores());

	}
	
    private static void sep() {
    	System.out.println("---------------------------------------------------------------------------");
    }
}
