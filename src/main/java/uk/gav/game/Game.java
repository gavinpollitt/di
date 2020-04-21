package uk.gav.game;

import java.util.List;
import java.util.stream.Collectors;

public class Game {
	
	private final GameContext context;
	
	private final GameResultProcessor resultProcessor;
	
	private List<Integer> p1Result;
	private List<Integer> p2Result;
	
	
	public Game(final GameContext context, final GameResultProcessor resultProcessor) {
		this.context = context;
		this.resultProcessor = resultProcessor;
	}
	
	public void play() {
		List<Die> dice = this.context.getDice();
		
		p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		
		this.resultProcessor.processResult(p1Result, p2Result);
	}

}
