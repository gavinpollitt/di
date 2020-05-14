package uk.gav.guice;

import uk.gav.game.DiceProvider;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.impl.BasicDiceProvider;
import uk.gav.game.impl.Game;
import uk.gav.game.impl.GameContext;
import uk.gav.game.impl.HighestRollResultProcessor;

public class AppCreate 
{
    public static void main( String[] args )
    {
    	DiceProvider provider = new BasicDiceProvider(6);
    	GameContext context = new GameContext(provider, 3);
    	GameResultProcessor resultProcessor = new HighestRollResultProcessor();
    	
    	Game g = new Game(context, resultProcessor);
    	
    	System.out.println(g.play());
    	
     }
}
