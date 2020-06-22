package uk.gav.nondi2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import uk.gav.nondi1.Players;

public class GameTWO {
	public static void main(String[] args) {
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));
		new GameTWO().play(players);
	}
	
	public void play(final Players players) {
		DiceFactory bdf4x6 = new BasicDiceFactory(6, 4);

		System.out.println(new HighestRollGame(bdf4x6, players).play());
		System.out.println(new HighestRollGame(bdf4x6, players).play());
		
		DiceFactory bdf5x6 = new BasicDiceFactory(6, 5);
		System.out.println(new DoubleUpGame(bdf5x6, players).play());

	}
}
