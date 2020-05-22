package uk.gav.nondi5.stats;

import java.util.List;

public class AverageThrowStat implements StatProducer {

	public AverageThrowStat() {
	}
	
	@Override
	public String analyse(final int sides, final List<List<Integer>> gameDice) {
		
		List<Integer> allDice = StatProducer.flattenRolls(gameDice);
		
		Double av = allDice.stream().reduce(0, (a,v) -> a+v)/(allDice.size()*1.0);

		return "Average Role: " + av;
	}
	
}
