package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

import uk.gav.game.DiceProvider;
import uk.gav.game.DoubleUpResultProcessor;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.StandardDiceProvider;
import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;

public class DoubleGameModule extends AbstractModule {

	@Override
	  protected void configure() {
	    bind(DiceProvider.class).to(StandardDiceProvider.class);
	    bind(GameResultProcessor.class).to(DoubleUpResultProcessor.class);
	    bind(Key.get(Integer.class, Sides.class)).toInstance(6);	    
	    bind(Key.get(Integer.class, Dice.class)).toInstance(5);	    
	  }
}
