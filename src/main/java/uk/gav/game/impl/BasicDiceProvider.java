package uk.gav.game.impl;

import javax.inject.Inject;

import uk.gav.game.DiceProvider;
import uk.gav.game.Die;
import uk.gav.game.annotation.Sides;

/**
 * 
 * @author regen
 *
 * 'Factory' for basic dice that provide the standard roles based on the number of sides.
 */
public class BasicDiceProvider implements DiceProvider {
	
	private final int sides;
	
	/**
	 * The 'sides' parameter value can be injected by the DI framework
	 * @param sides The number of sides that the created dice will have
	 */
	@Inject
	public BasicDiceProvider(@Sides final Integer sides) {
		this.sides = sides;
	}

	@Override
	public Die get() {
		return new BasicDie(this.sides);
	}
	
	@Override
	public int getSides() {
		return this.sides;
	}
}
