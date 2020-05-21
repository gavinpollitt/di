package uk.gav.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.gav.game.impl.Game;

public class AppInject 
{
    public static void main( String[] args )
    {
    	sep();
    	System.out.println("Game One");
        Injector injector = Guice.createInjector(new CommonModule(), new QuickGameModule(), new LadsPlayerModule());
        Game g = injector.getInstance(Game.class);
        System.out.println(g.play());
        System.out.println(g.play());
        System.out.println(g.play());
        System.out.println(g.getGameScores());

    	sep();
        System.out.println("Game Two");
        g = injector.getInstance(Game.class);
        System.out.println(g.play());
        System.out.println(g.getGameScores());
        
    	sep();
    	System.out.println("\nGame Three");
        injector = Guice.createInjector(new CommonModule(),new DoubleGameModule(), new LadiesPlayerModule());
        g = injector.getInstance(Game.class);        
        System.out.println(g.play());
        System.out.println(g.play());
        System.out.println(g.getGameScores());
    }
    
    private static void sep() {
    	System.out.println("---------------------------------------------------------------------------");
    }
}
