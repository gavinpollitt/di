package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.Multibinder;

import uk.gav.game.DiceProvider;
import uk.gav.game.GameResultProcessor;
import uk.gav.game.annotation.Dice;
import uk.gav.game.annotation.Sides;
import uk.gav.game.impl.BasicDiceProvider;
import uk.gav.game.impl.GameContext;
import uk.gav.game.impl.HighestRollResultProcessor;
import uk.gav.game.stats.AverageThrowStat;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;

public class QuickGameModule extends AbstractModule {

	@Override
	  protected void configure() {
	    bind(GameContext.class);   // If not supplied, will perform late binding if allowed using classpath
	    bind(DiceProvider.class).to(BasicDiceProvider.class);
	    bind(GameResultProcessor.class).to(HighestRollResultProcessor.class);
	    bind(Key.get(Integer.class, Sides.class)).toInstance(12);	    
	    bind(Key.get(Integer.class, Dice.class)).toInstance(3);	
	    
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(AverageThrowStat.class);
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(FaceCountStat.class);
	  }
}
