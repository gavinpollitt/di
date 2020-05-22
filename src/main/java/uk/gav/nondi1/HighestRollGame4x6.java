package uk.gav.nondi1;

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
	
	private final Players players;

	public HighestRollGame4x6(final Players players) {
		this.dice = new ArrayList<>(sides);
		this.players = players;
		IntStream.range(0, this.diceCount).forEach((i) -> dice.add(createDie()));
	}

	public String play() {
		List<Integer> p1Result = dice.stream().map(Die::roll).collect(Collectors.toList());
		List<Integer> p2Result = dice.stream().map(Die::roll).collect(Collectors.toList());

		int p1Score = p1Result.stream().reduce(0, (a, i) -> a + i);
		int p2Score = p2Result.stream().reduce(0, (a, i) -> a + i);

		if (p1Score > p2Score) {
			return players.getPlayerName(1) + " with " + p1Score + " beats " + players.getPlayerName(2) + " with " + p2Score;
		} else if (p2Score > p1Score) {
			return players.getPlayerName(2) + " with " + p2Score + " beats " + players.getPlayerName(1) + " with " + p1Score;
		} else {
			return "Both players rolled: " + p1Score + ". It's a draw";
		}
	}

	protected Die createDie() {
		return new BasicDie(this.sides);
	}

}
