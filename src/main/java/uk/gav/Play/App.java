package uk.gav.Play;

import uk.gav.game.DiceProvider;
import uk.gav.game.Game;
import uk.gav.game.GameContext;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.HighestRollResultProcessor;

public class App 
{
    public static void main( String[] args )
    {
    	DiceProvider provider = new DiceProvider(6);
    	GameContext context = new GameContext(provider, 3);
    	GameResultProcessor resultProcessor = new HighestRollResultProcessor();
    	
    	Game g = new Game(context, resultProcessor);
    	
    	System.out.println(g.play());
    }
}
