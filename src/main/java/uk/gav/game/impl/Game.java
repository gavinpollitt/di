package uk.gav.game.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import uk.gav.game.Die;
import uk.gav.game.GameResultProcessor;

/**
 * 
 * @author regen
 *
 * A single iteration of the current 'game of dice'. The type of game will be determined by the injected classes
 */
public class Game {
	
	private final GameContext context;
	
	private final GameResultProcessor resultProcessor;
	
	@Inject
	public Game(final GameContext context, final GameResultProcessor resultProcessor) {
		this.context = context;
		this.resultProcessor = resultProcessor;
	}
	
	public String play() {
		System.out.println(this.context);
		List<Die> dice = this.context.getDice();
		
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		
		return this.resultProcessor.processResult(p1Result, p2Result);
	}

}
