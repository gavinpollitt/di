package uk.gav.nondi4;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

 import uk.gav.nondi4.interfaces.DiceFactory;
import uk.gav.nondi4.interfaces.Die;
import uk.gav.nondi4.interfaces.GameResultProcessor;
import uk.gav.nondi4.stats.StatProducer;

public class Game {
	
	private final DiceFactory diceFactory;
	
	private final List<Die> dice;

	private final GameResultProcessor resultProcessor;
	
	private final Players players;
	
	private final Set<StatProducer> statProducers;	
		
	public Game(final DiceFactory diceFactory, final Players players, final GameResultProcessor resultProcessor, final Set<StatProducer> statProducers) {
		this.diceFactory = diceFactory;
		this.dice = diceFactory.create();
		this.resultProcessor = resultProcessor;
		this.players = players;
		this.statProducers = statProducers;
	}
	
	public String play() {
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		if (this.statProducers != null) {
			this.statProducers.stream().map(p -> p.analyse(this.diceFactory.getSides(), StatProducer.listUp(p1Result, p2Result))).forEach(System.out::println);
		}
		return this.resultProcessor.processResult(players, p1Result, p2Result);
	}
	
	public String getGameScores() {
		return this.players.toString();
	}
}
