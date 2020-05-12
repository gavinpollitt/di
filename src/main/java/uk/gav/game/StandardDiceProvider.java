package uk.gav.game;

import javax.inject.Inject;

import uk.gav.game.annotation.Sides;

public class StandardDiceProvider implements DiceProvider {
	
	private final int sides;
	
	@Inject
	public StandardDiceProvider(@Sides final Integer sides) {
		this.sides = sides;
	}

	@Override
	public Die get() {
		return new Die(this.sides);
	}
	
	@Override
	public int getSides() {
		return this.sides;
	}
}
