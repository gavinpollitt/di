package uk.gav.nondiONE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import uk.gav.game.Die;
import uk.gav.game.impl.BasicDie;

public class HighestRollGame4x6 {

	private final int sides = 6;
	private final int diceCount = 4;

	private final List<Die> dice;

	public HighestRollGame4x6() {
		this.dice = new ArrayList<>(sides);
		IntStream.range(0, this.diceCount).forEach((i) -> dice.add(createDie()));
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

	protected Die createDie() {
		return new BasicDie(this.sides);
	}

}
