package uk.gav.game;

public class Die {
	private final int sides;
	
	public Die(final Integer sides) {
		this.sides = sides;		
	}
	
	public int roll() {
		return (int)(Math.random()*this.sides) + 1;
	}
}
