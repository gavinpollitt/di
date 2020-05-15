package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.Multibinder;

import uk.gav.game.DiceProvider;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.BasicDiceProvider;
import uk.gav.game.impl.DoubleUpResultProcessor;
import uk.gav.game.stats.AverageThrowStat;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;

public class DoubleGameModule extends AbstractModule {

	@Override
	  protected void configure() {
	    bind(DiceProvider.class).to(BasicDiceProvider.class);
	    bind(GameResultProcessor.class).to(DoubleUpResultProcessor.class);
	    bind(Key.get(Integer.class, Sides.class)).toInstance(6);	    
	    bind(Key.get(Integer.class, Dice.class)).toInstance(5);	  
	    
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(AverageThrowStat.class);
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(FaceCountStat.class);

	  }
}
