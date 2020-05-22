package uk.gav.nondi1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GameONE {
	public static void main(String[] args) {
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));
		System.out.println(new HighestRollGame2x12(players).play());
		System.out.println(new HighestRollGame4x6(players).play());
		System.out.println(new HighestRollGame4x6(players).play());
		System.out.println(new DoubleUpGame(players).play());
	}
}
