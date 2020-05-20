package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

import uk.gav.game.GameResultProcessor;
import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.HighestRollResultProcessor;

public class QuickGameModule extends AbstractModule {

	@Override
	  protected void configure() {
	    bind(GameResultProcessor.class).to(HighestRollResultProcessor.class);
	    bind(Key.get(Integer.class, Sides.class)).toInstance(12);	    
	    bind(Key.get(Integer.class, Dice.class)).toInstance(3);	
	  }
}
