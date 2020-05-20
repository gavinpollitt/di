package uk.gav.nondiTWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDiceFactory extends BasicDiceFactory {

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
	
	public TestDiceFactory(final Integer sides, final int dice) {
		super(sides, dice);
		DieControlled.init(ROLLS.get(sides));
	}

	@Override
	protected Die createDie() {
		return new DieControlled();
	}
}
