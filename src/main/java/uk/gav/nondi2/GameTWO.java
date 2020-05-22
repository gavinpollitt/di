package uk.gav.nondi2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import uk.gav.nondi1.Players;

public class GameTWO {
	public static void main(String[] args) {		
		BasicDiceFactory bdf4x6 = new BasicDiceFactory(6, 4);
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));

		System.out.println(new HighestRollGame(bdf4x6, players).play());
		System.out.println(new HighestRollGame(bdf4x6, players).play());
		
		BasicDiceFactory bdf5x6 = new BasicDiceFactory(6, 5);
		System.out.println(new DoubleUpGame(bdf5x6, players).play());

	}
}
