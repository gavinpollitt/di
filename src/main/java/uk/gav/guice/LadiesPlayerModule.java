package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

public class LadiesPlayerModule extends AbstractModule {

	@Override
	protected void configure() {
	    Multibinder.newSetBinder(binder(), String.class, Names.named("players")).addBinding().toInstance("Tracy");	  
	    Multibinder.newSetBinder(binder(), String.class, Names.named("players")).addBinding().toInstance("Sharon");	  
	}
}
