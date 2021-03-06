package uk.gav.nondi2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BasicDiceFactory implements DiceFactory {
	
	protected final int sides;
	
	protected final int dice;
	
	public BasicDiceFactory(final Integer sides, final int dice) {
		this.dice = dice;
		this.sides = sides;
	}
	
	@Override
	public List<Die> create() {
		List<Die> dList = new ArrayList<>(this.dice);
		IntStream.range(0, this.dice).forEach((i) -> dList.add(createDie())); 
		return dList;
	}

	protected Die createDie() {
		return new BasicDie(this.sides);
	}
}
