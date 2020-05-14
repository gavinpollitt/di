package uk.gav.game.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.gav.game.DiceProvider;
import uk.gav.game.Die;
import uk.gav.game.annotation.Dice;

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
	public GameContext(final DiceProvider provider, final @Dice int dice) {
		this.provider = provider;
		this.dice = new ArrayList<>(dice);
		
		IntStream.range(0, dice).forEach(i -> this.dice.add(this.provider.get()));
	}
	
	public List<Die> getDice() {
		return this.dice;
	}
	
	public String toString() {
		String pl = this.dice.size() > 1?"dice":"die";
		return "Playing with " + this.dice.size() + " x " + this.provider.getSides() + " sided " + pl;
	}
	
}
