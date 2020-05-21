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
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.Game;
import uk.gav.game.impl.HighestRollResultProcessor;
import uk.gav.game.logging.Logger;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;
import uk.gav.guice.LadsPlayerModule;

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
			    
			    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(FaceCountStat.class);
			    
				Logger logger = new Logger("TEST", false);
				bind(Logger.class).annotatedWith(Names.named("game")).toInstance(logger);			    
				bind(Logger.class).annotatedWith(Names.named("system")).toInstance(logger);			    
				bind(Logger.class).annotatedWith(Names.named("statistic")).toInstance(logger);			    

			}
			
		};
		
        injector = Guice.createInjector(module, new LadsPlayerModule());		
	}
	
	@Test   
	public void play1() {
		final String res = "Bob with 19 beats Todd with 8";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
	}
	
	@Test
	public void play2() {
		String res = "Todd with 23 beats Bob with 8";
        Game g = injector.getInstance(Game.class);
        String result = g.play();
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
        
        result = g.play();
        res = "Both players rolled: 19. It's a draw";
        assertTrue("Output should be '" + res + "', but was " + result, result.equals(res));
        
        result = g.getGameScores();
        res = "Bob won 0 gamesTodd won 1 game";
        assertTrue("Output should be '" + res + "', but was " + result, result.replaceAll("\\n", "").equals(res));
	}	
}
