package uk.gav.nondi2;

import java.util.List;
import java.util.Stack;

import uk.gav.nondi2.Die;

public class DieControlled implements Die {
	private final static Stack<Integer> rolls = new Stack<>();
	private static List<Integer> content;

	public static void init(final List<Integer> c) {
		rolls.addAll(c);
		content = c;
	}
	
	public DieControlled() {
	}

	@Override
	public int roll() {
		if (rolls.isEmpty()) {
			rolls.addAll(content);
		}
		return rolls.pop();
	}
	
}
