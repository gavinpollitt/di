package uk.gav.game;

import java.util.List;

public interface GameResultProcessor {

	public void processResult(final List<Integer> player1, final List<Integer> player2);
}
