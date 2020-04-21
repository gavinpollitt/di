package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

import uk.gav.game.DiceProvider;
import uk.gav.game.GameContext;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.HighestRollResultProcessor;
import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;

public class GameModule extends AbstractModule {

	@Override
	  protected void configure() {
	    bind(GameContext.class);   // If not supplied, will perform late binding if allowed using classpath
	    bind(DiceProvider.class);
	    bind(GameResultProcessor.class).to(HighestRollResultProcessor.class);
	    bind(Key.get(Integer.class, Sides.class)).toInstance(6);	    
	    bind(Key.get(Integer.class, Dice.class)).toInstance(3);	    
	  }
}
