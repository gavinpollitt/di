package uk.gav.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.gav.game.annotation.Sides;

@Singleton
public class TestDiceProvider implements DiceProvider {

	private final static Map<Integer, List<Integer>> ROLLS = new HashMap<>();
	
	private final static List<Integer> SIX_CONTENT = Arrays
			.asList(new Integer[] { 4, 3, 5, 1, 1, 6, 5, 2, 1, 2, 2, 2, 2, 2, 3, 4, 5, 2 });
	private final static List<Integer> TWELVE_CONTENT = Arrays
			.asList(new Integer[] { 7, 12, 1, 7, 4, 4, 11, 12, 10, 9});

	static {
		//Reverse order as it's in a stack
		Collections.reverse(SIX_CONTENT);
		Collections.reverse(TWELVE_CONTENT);
		ROLLS.put(6, SIX_CONTENT);
		ROLLS.put(12, TWELVE_CONTENT);
	}
	
	private final int sides;
	
	@Inject
	public TestDiceProvider(final @Sides int sides) {
		this.sides = sides;
		DieControlled.init(ROLLS.get(sides));
	}

	@Override
	public Die get() {
		return new DieControlled();
	}

	@Override
	public int getSides() {
		return this.sides;
	}
}
