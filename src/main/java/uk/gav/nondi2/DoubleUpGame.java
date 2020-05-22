package uk.gav.nondi2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import uk.gav.nondi1.Players;

public class DoubleUpGame {

	private final List<Die> dice;
	
	private final Players players;

	public DoubleUpGame(final DiceFactory diceFactory, final Players players) {		
		this.dice = diceFactory.create();
		this.players = players;
	}
	
	public String play() {
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		Map<Integer,Integer> p1Rolls = checkDoubles(p1Result);
		Map<Integer,Integer> p2Rolls = checkDoubles(p2Result);
		
		String p1Db = reportDoubles(p1Rolls);
		String p2Db = reportDoubles(p2Rolls);
		
		int p1Score = total(p1Rolls);
		int p2Score = total(p2Rolls);
		
		if (p1Score > p2Score) {
			return players.getPlayerName(1) + " " + p1Db + " giving score " + p1Score + " beats " + players.getPlayerName(2) + " who " + p2Db + " giving score "+ p2Score;
		}
		else if (p2Score > p1Score) {
			return players.getPlayerName(2)+ " " + p2Db + " giving score " + p2Score + " beats " + players.getPlayerName(2) + " who " + p1Db + " giving score "+ p1Score;
		}
		else {
			return "Both players rolled: " + p1Score + ". It's a draw";			
		}
	}
	
	/**
	 * 
	 * @param p the integers representing the rolls on the dice
	 * @return The map of r -> c   where r is the number rolled and c is the number of times rolled.
	 */
	private static Map<Integer,Integer> checkDoubles(final List<Integer> p) {
		Map<Integer,Integer> out = new HashMap<>();
		
		for (Integer i: p) {
			Integer cnt = out.get(i);
			
			out.put(i, cnt==null?1:cnt+1);
		}
		
		return out;
	}
	
	/**
	 * 
	 * @param dbs The map of r -> c   where r is the number rolled and c is the number of times rolled.
	 * @return The total score for this role.
	 */
	private static int total(final Map<Integer,Integer> dbs) {
		int tot = 0;
		for (Integer r: dbs.keySet()) {
			Integer m = dbs.get(r);
			tot += (m*m*r);
		}

		return tot;
	}
	
	/**
	 * 
	 * @param dbs The map of r -> c   where r is the number rolled and c is the number of times rolled.
	 * @return The prinatable version of the result.
	 */
	private static String reportDoubles(final Map<Integer,Integer> dbs) {
		String out = "rolled ";
		
		String sep = "";
		for (Integer r: dbs.keySet()) {
			Integer m = dbs.get(r);
			if (m > 1) {
				out += sep + (m + "X" + r);
				out += " = " + (m*m*r);
				sep = " and ";
			}
		}

		if (sep.length() == 0) {
			out+= "no doubles";
		}
		
		return out;
	}


}
