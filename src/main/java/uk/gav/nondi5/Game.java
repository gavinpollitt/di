package uk.gav.nondi5;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import uk.gav.nondi5.interfaces.Die;
import uk.gav.nondi5.interfaces.GameResultProcessor;
import uk.gav.nondi5.stats.StatProducer;


public class Game {
	
	private final GameData context;
	
	private final GameResultProcessor resultProcessor;
	
	private final Set<StatProducer> statProducers;
	
	public Game(final GameData context, 
				final GameResultProcessor resultProcessor, 
				final Set<StatProducer> statProducers) {
		this.context = context;
		this.resultProcessor = resultProcessor;
		this.statProducers = statProducers;
	}
	
	public String play() {
		List<Die> dice = this.context.getDice();
		
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		if (this.statProducers != null) {
			this.statProducers.stream().map(p -> p.analyse(this.context.getSides(), StatProducer.listUp(p1Result, p2Result))).forEach(System.out::println);
		}
		
		return this.resultProcessor.processResult(this.context.getPlayers(), p1Result, p2Result);
	}

	public String getGameScores() {
		return this.context.getPlayers().toString();
	}
}
