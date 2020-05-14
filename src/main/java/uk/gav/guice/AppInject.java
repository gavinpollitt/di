package uk.gav.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

import uk.gav.game.impl.Game;

public class AppInject 
{
    public static void main( String[] args )
    {
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
