package uk.gav.nondi4;

import uk.gav.nondi4.interfaces.Die;

public class BasicDie implements Die{
	private final int sides;
	
	public BasicDie(final Integer sides) {
		this.sides = sides;		
	}
	
	public int roll() {
		return (int)(Math.random()*this.sides) + 1;
	}
}
