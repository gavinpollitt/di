package uk.gav.nondi5;

import java.util.List;

import uk.gav.nondi5.interfaces.DiceFactory;
import uk.gav.nondi5.interfaces.Die;


public final class GameData {
	
	private final DiceFactory factory;
	
	private final List<Die> dice;
	
	private final Players players;
	
	public GameData(final DiceFactory factory, final Players players) {
		this.factory = factory;
		this.dice = factory.create();
		this.players = players;
	}
	
	public List<Die> getDice() {
		return this.dice;
	}
	
	public String toString() {
		String pl = this.dice.size() > 1?"dice":"die";
		return "Playing with " + this.factory.getDice() + " x " + this.getSides() + " sided " + pl;
	}
	
	public int getSides() {
		return this.factory.getSides();
	}

	public Players getPlayers() {
		return players;
	}
	
}
