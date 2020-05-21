package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

import uk.gav.game.DiceProvider;
import uk.gav.game.impl.BasicDiceProvider;
import uk.gav.game.logging.Logger;
import uk.gav.game.stats.AverageThrowStat;
import uk.gav.game.stats.FaceCountStat;
import uk.gav.game.stats.StatProducer;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
	    //bind(GameContext.class);   // If not supplied, will perform late binding if allowed using classpath
	    bind(DiceProvider.class).to(BasicDiceProvider.class);
		
		this.bindLogger("Game", false);
		this.bindLogger("System", true);
	    this.bindLogger("Statistic", false);
	    
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(AverageThrowStat.class);
	    Multibinder.newSetBinder(binder(), StatProducer.class).addBinding().to(FaceCountStat.class);

	}
	
	private void bindLogger(final String name, final boolean active) {
		Logger logger = new Logger(name, active);
	    bind(Logger.class).annotatedWith(Names.named(name.toLowerCase())).toInstance(logger);		
	}
}
