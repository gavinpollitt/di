package uk.gav.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.gav.game.Game;

public class App 
{
    public static void main( String[] args )
    {
//    	DiceProvider provider = new DiceProvider(6);
//    	GameContext context = new GameContext(provider, 3);
//    	GameResultProcessor resultProcessor = new HighestRollResultProcessor();
//    	
//    	Game g = new Game(context, resultProcessor);
//    	
//    	System.out.println(g.play());
    	
    	System.out.println("Game One");
        Injector injector = Guice.createInjector(new QuickGameModule());
        Game g = injector.getInstance(Game.class);
        System.out.println(g.play());
        
    	System.out.println("\nGame Two");
        injector = Guice.createInjector(new DoubleGameModule());
        g = injector.getInstance(Game.class);        
        System.out.println(g.play());


    }
}
