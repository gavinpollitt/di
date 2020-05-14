package uk.gav.game;

import com.google.inject.Provider;

/**
 * 
 * @author regen
 *
 * Root interface of classes that act as Die factories.
 */
public interface DiceProvider extends Provider<Die> {
	public int getSides();
}
