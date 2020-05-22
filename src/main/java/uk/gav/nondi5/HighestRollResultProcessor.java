package uk.gav.nondi5;

import java.util.List;

import uk.gav.nondi5.interfaces.GameResultProcessor;

public class HighestRollResultProcessor implements GameResultProcessor {

	
	@Override
	public String processResult(Players players, List<Integer> player1, List<Integer> player2) {
		int p1Score = player1.stream().reduce(0, (a, i) -> a + i);
		int p2Score = player2.stream().reduce(0, (a, i) -> a + i);
		
		if (p1Score > p2Score) {
			players.won(1);
			return players.getPlayerName(1) + " with " + p1Score + " beats " + players.getPlayerName(2) + " with " + p2Score;
		}
		else if (p2Score > p1Score) {
			players.won(2);
			return players.getPlayerName(2) + " with " + p2Score + " beats " + players.getPlayerName(1) + " with " + p1Score;
		}
		else {
			return "Both players rolled: " + p1Score + ". It's a draw";			
		}
	}

}
