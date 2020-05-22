package uk.gav.nondi3;

import java.util.List;
import java.util.stream.Collectors;

import uk.gav.nondi3.interfaces.DiceFactory;
import uk.gav.nondi3.interfaces.Die;
import uk.gav.nondi3.interfaces.GameResultProcessor;

public class Game {
	
	private final List<Die> dice;

	private final GameResultProcessor resultProcessor;
	
	private final Players players;
	
	
	public Game(final DiceFactory diceFactory, final Players players, final GameResultProcessor resultProcessor) {
		this.dice = diceFactory.create();
		this.resultProcessor = resultProcessor;
		this.players = players;
	}
	
	public String play() {
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		return this.resultProcessor.processResult(players, p1Result, p2Result);
	}
}
