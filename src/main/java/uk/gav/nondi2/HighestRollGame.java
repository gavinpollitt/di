package uk.gav.nondi2;

import java.util.List;
import java.util.stream.Collectors;

import uk.gav.nondi1.Players;

public class HighestRollGame {

	private final List<Die> dice;
	
	private final Players players;

	public HighestRollGame(final DiceFactory diceFactory, final Players players) {
		this.dice = diceFactory.create();
		this.players = players;
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

}
