package uk.gav.nondi3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import uk.gav.nondi3.interfaces.DiceFactory;
import uk.gav.nondi3.interfaces.GameResultProcessor;

public class GameTHREE {
	public static void main(String[] args) {		
		List<String> pList = Arrays.asList("Bob","Todd");
		Players players = new Players(new HashSet<>(pList));
		new GameTHREE().play(players);
	}
	
	public void play(final Players players) {

		DiceFactory df = new BasicDiceFactory(6, 4);
		GameResultProcessor rp = new HighestRollResultProcessor();
		Game g = new Game(df,players,rp);
		System.out.println(g.play());
		System.out.println(g.play());
		
		df = new BasicDiceFactory(6, 5);
		rp = new DoubleUpResultProcessor();
		g = new Game(df, players,rp);
		System.out.println(new Game(df, players,rp).play());

	}
}
