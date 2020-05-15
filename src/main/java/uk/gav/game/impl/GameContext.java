package uk.gav.game.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.gav.game.DiceProvider;
import uk.gav.game.Die;

/**
 * 
 * @author regen
 *
 * Singleton holder of the current game environment allow injections of the provider of the dice and
 * the number of dice to use.
 */
@Singleton
public final class GameContext {
	
	private final DiceProvider provider;
	
	private final List<Die> dice;
	
	@Inject
	public GameContext(final DiceProvider provider) {
		this.provider = provider;
		this.dice = provider.get();
	}
	
	public List<Die> getDice() {
		return this.dice;
	}
	
	public String toString() {
		String pl = this.dice.size() > 1?"dice":"die";
		return "Playing with " + this.provider.getDice() + " x " + this.getSides() + " sided " + pl;
	}
	
	public int getSides() {
		return this.provider.getSides();
	}
	
}
