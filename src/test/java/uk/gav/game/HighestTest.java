package uk.gav.game;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;

import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.Game;
import uk.gav.game.impl.HighestRollResultProcessor;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HighestTest
{
	private static Injector injector;
	
	@BeforeClass
	public static void init() {
		AbstractModule module = new AbstractModule() {
			@Override
			protected void configure() {
				super.configure();
			    bind(DiceProvider.class).to(TestDiceProvider.class);
			    bind(GameResultProcessor.class).to(HighestRollResultProcessor.class);
			    bind(Key.get(Integer.class, Dice.class)).toInstance(2);	 
			    bind(Key.get(Integer.class, Sides.class)).toInstance(12);	    
			}
			
		};
		
        injector = Guice.createInjector(module);		
	}
	
	@Test   
	public void play1() {
		final String res = "Player 1 with 19 beats player 2 with 8";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Player 2 with 23 beats player 1 with 8";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play3() {
		final String res = "Both players rolled: 19. It's a draw";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
}
