package uk.gav.nondiTWO;

import java.util.List;
import java.util.stream.Collectors;

public class HighestRollGame {

	private final List<Die> dice;

	public HighestRollGame(final DiceFactory diceFactory) {
		
		this.dice = diceFactory.create();
	}

	public String play() {
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		int p1Score = p1Result.stream().reduce(0, (a, i) -> a + i);
		int p2Score = p2Result.stream().reduce(0, (a, i) -> a + i);

		if (p1Score > p2Score) {
			return "Player 1 with " + p1Score + " beats player 2 with " + p2Score;
		} else if (p2Score > p1Score) {
			return "Player 2 with " + p2Score + " beats player 1 with " + p1Score;
		} else {
			return "Both players rolled: " + p1Score + ". It's a draw";
		}
	}

}
