package uk.gav.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class GameContext {
	
	private final DiceProvider provider;
	
	private final List<Die> dice;
	
	@Inject
	public GameContext(final DiceProvider provider, final int dice) {
		this.provider = provider;
		this.dice = new ArrayList<>(dice);
		
		IntStream.range(0, dice - 1).forEach(i -> this.dice.add(this.provider.get()));
	}
	
	public List<Die> getDice() {
		return this.dice;
	}
	
}
