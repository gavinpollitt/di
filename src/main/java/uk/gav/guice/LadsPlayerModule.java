package uk.gav.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;

public class LadsPlayerModule extends AbstractModule {

	@Override
	protected void configure() {
	    Multibinder.newSetBinder(binder(), String.class, Names.named("players")).addBinding().toInstance("Bob");	  
	    Multibinder.newSetBinder(binder(), String.class, Names.named("players")).addBinding().toInstance("Todd");	  
	}
}
