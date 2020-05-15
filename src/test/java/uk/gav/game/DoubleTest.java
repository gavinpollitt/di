package uk.gav.game;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.multibindings.OptionalBinder;

import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.DoubleUpResultProcessor;
import uk.gav.game.impl.Game;
import uk.gav.game.stats.AverageThrowStat;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DoubleTest
{
	private static Injector injector;
	
	@BeforeClass
	public static void init() {
		AbstractModule module = new AbstractModule() {
			@Override
			protected void configure() {
				super.configure();
			    bind(DiceProvider.class).to(TestDiceProvider.class);
			    bind(GameResultProcessor.class).to(DoubleUpResultProcessor.class);
			    bind(Key.get(Integer.class, Dice.class)).toInstance(3);	 
			    bind(Key.get(Integer.class, Sides.class)).toInstance(6);	    

			    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(AverageThrowStat.class);

			}
			
		};
		
        injector = Guice.createInjector(module);		
	}
	
	@Test   
	public void play1() {
		final String res = "Player 1 rolled no doubles giving score 12 beats player 2 who rolled 2X1 = 4 giving score 10";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		final String res = "Player 2 rolled 3X2 = 18 giving score 18 beats player 1 who rolled no doubles giving score 8";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play3() {
		final String res = "Both players rolled: 11. It's a draw";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        //System.out.println(result);
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
}
