package uk.gav.nondi1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
	private final List<Player> players;
		
	public Players(final Set<String> players) {
		super();
		this.players = players.stream().map(Player::new).collect(Collectors.toList());
	}

	public String getPlayerName(final int p) {
		return players.get(p-1).name;
	}
	
	public int getPlayerWon(final int p) {
		return players.get(p-1).won;		
	}

	public void won(final int p) {
		players.get(p-1).won+=1;
	}
	
	public String toString() {
		return this.players.stream().map(p -> p.toString()).reduce("", (a, v) -> a + v + "\n");
	}
	
	private static class Player {
		private String name;
		private int won = 0;
		
		public Player(final String name) {
			this.name = name;
		}
		
		public String toString() {
			return name + " won " + won + (won==1?" game":" games");
		}
	}
}
