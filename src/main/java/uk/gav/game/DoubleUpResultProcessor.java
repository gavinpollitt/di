package uk.gav.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class DoubleUpResultProcessor implements GameResultProcessor {

	public static void main(String[] args) {
		List<Integer> check = Arrays.asList(1,2,3,4,2,5,5,2);
		System.out.println(checkDoubles(check));
		System.out.println(total(checkDoubles(check)));
		System.out.println(reportDoubles(checkDoubles(check)));
	}
	
	@Override
	public String processResult(final List<Integer> player1, final List<Integer> player2) {
		System.out.println("Doubles count double and so on");
		Map<Integer,Integer> p1Rolls = checkDoubles(player1);
		Map<Integer,Integer> p2Rolls = checkDoubles(player2);
		
		String p1Db = reportDoubles(p1Rolls);
		String p2Db = reportDoubles(p2Rolls);
		
		int p1Score = total(p1Rolls);
		int p2Score = total(p2Rolls);
		
		if (p1Score > p2Score) {
			return "Player 1 " + p1Db + " giving score " + p1Score + " beats player 2 who " + p2Db + " giving score "+ p2Score;
		}
		else if (p2Score > p1Score) {
			return "Player 2 " + p2Db + " giving score " + p2Score + " beats player 1 who " + p1Db + " giving score "+ p1Score;
		}
		else {
			return "Both players rolled: " + p1Score + ". It's a draw";			
		}
	}
	
	private static Map<Integer,Integer> checkDoubles(final List<Integer> p) {
		Map<Integer,Integer> out = new HashMap<>();
		
		for (Integer i: p) {
			Integer cnt = out.get(i);
			
			if (cnt == null) {
				out.put(i, 1);
			}
			else {
				out.put(i, cnt+1);
			}
		}
		
		return out;
	}
	
	private static int total(final Map<Integer,Integer> dbs) {
		int tot = 0;
		for (Integer r: dbs.keySet()) {
			Integer m = dbs.get(r);
			tot += (m*m*r);
		}

		return tot;
	}
	
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
