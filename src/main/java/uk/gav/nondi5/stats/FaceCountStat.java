package uk.gav.nondi5.stats;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class FaceCountStat implements StatProducer {

	public FaceCountStat() {
	}
	
	@Override
	public String analyse(final int sides, final List<List<Integer>> gameDice) {
		
		List<Integer> allDice = StatProducer.flattenRolls(gameDice);
		
		Map<Integer, Integer> range = new TreeMap<>();
		IntStream.range(1, sides+1).forEach(i -> range.put(i, 0));
		
		allDice.stream().forEach(d -> range.put(d, range.get(d)+1));
		
		String output = range.keySet().stream().map(k -> ""+k).reduce("", (a, v) -> a.concat("[" + v + "," + range.get(Integer.parseInt(v)) + "]"));
		
		return output;
	}
	

}
