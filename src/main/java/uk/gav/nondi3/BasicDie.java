package uk.gav.nondi3;

import uk.gav.nondi3.interfaces.Die;

public class BasicDie implements Die{
	private final int sides;
	
	public BasicDie(final Integer sides) {
		this.sides = sides;		
	}
	
	public int roll() {
		return (int)(Math.random()*this.sides) + 1;
	}
}
