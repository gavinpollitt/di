package uk.gav.game;

import javax.inject.Inject;
import javax.inject.Provider;

public class DiceProvider implements Provider<Die> {
	
	private final int sides;
	
	@Inject
	public DiceProvider(final int sides) {
		this.sides = sides;
	}

	@Override
	public Die get() {
		// TODO Auto-generated method stub
		return new Die(this.sides);
	}

}
