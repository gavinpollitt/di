package uk.gav.game.stats;

import java.util.Arrays;
import java.util.List;

public class AverageThrowStat implements StatProducer {

	@Override
	public String analyse(final int sides, final List<List<Integer>> gameDice) {
		
		List<Integer> allDice = StatProducer.flattenRolls(gameDice);
		
		Double av = allDice.stream().reduce(0, (a,v) -> a+v)/(allDice.size()*1.0);

		return "Average Role: " + av;
	}
	
	public static void main(String[] args) {
		List<Integer> one = Arrays.asList(3,5,2,3,1,1);
		List<Integer> two = Arrays.asList(3,3,3);
		List<Integer> three = Arrays.asList(1,2,4,5,6);
		
		List<List<Integer>> lists = Arrays.asList(one,two,three);
		
		System.out.println(new AverageThrowStat().analyse(6, lists));
	}
}
