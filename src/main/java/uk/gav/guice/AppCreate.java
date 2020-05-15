package uk.gav.guice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import uk.gav.game.DiceProvider;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.impl.BasicDiceProvider;
import uk.gav.game.impl.Game;
import uk.gav.game.impl.GameContext;
import uk.gav.game.impl.HighestRollResultProcessor;
import uk.gav.game.stats.AverageThrowStat;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;

public class AppCreate 
{
    public static void main( String[] args )
    {
    	DiceProvider provider = new BasicDiceProvider(6,3);
    	GameContext context = new GameContext(provider);
    	GameResultProcessor resultProcessor = new HighestRollResultProcessor();
    	Set<StatProducer> statProducers = new HashSet<>(Arrays.asList(new AverageThrowStat(), new FaceCountStat()));
    	
    	Game g = new Game(context, resultProcessor, statProducers);
    	
    	System.out.println(g.play());
    	
     }
}
