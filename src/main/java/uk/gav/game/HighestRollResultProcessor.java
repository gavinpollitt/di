package uk.gav.game;

import java.util.List;

public class HighestRollResultProcessor implements GameResultProcessor {

	@Override
	public String processResult(final List<Integer> player1, final List<Integer> player2) {
		System.out.println("Highest Score Wins");
		int p1Score = player1.stream().reduce(0, (a, i) -> a + i);
		int p2Score = player2.stream().reduce(0, (a, i) -> a + i);
		
		if (p1Score > p2Score) {
			return "Player 1 with " + p1Score + " beats player 2 with " + p2Score;
		}
		else if (p2Score > p1Score) {
			return "Player 2 with " + p2Score + " beats player 1 with " + p1Score;
		}
		else {
			return "Both players rolled: " + p1Score + ". It's a draw";			
		}
	}

}
