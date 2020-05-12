package uk.gav.game;

import com.google.inject.Provider;

public interface DiceProvider extends Provider<Die> {
	public int getSides();
}
