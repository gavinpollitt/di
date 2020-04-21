package uk.gav.game;

import javax.inject.Inject;
import javax.inject.Provider;

import uk.gav.game.annotation.Sides;

public class DiceProvider implements Provider<Die> {
	
	private final int sides;
	
	@Inject
	public DiceProvider(@Sides final Integer sides) {
		this.sides = sides;
	}

	@Override
	public Die get() {
		// TODO Auto-generated method stub
		return new Die(this.sides);
	}
	
	public int getSides() {
		return this.sides;
	}
}
