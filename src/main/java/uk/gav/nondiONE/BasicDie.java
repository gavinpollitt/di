package uk.gav.nondiONE;

import uk.gav.game.Die;

public class BasicDie implements Die{
	private final int sides;
	
	public BasicDie(final Integer sides) {
		this.sides = sides;		
	}
	
	public int roll() {
		return (int)(Math.random()*this.sides) + 1;
	}
}
