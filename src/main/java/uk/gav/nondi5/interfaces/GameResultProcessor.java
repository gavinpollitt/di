package uk.gav.nondi5.interfaces;

import java.util.List;

import uk.gav.nondi5.Players;

public interface GameResultProcessor {

	public String processResult(final Players players, final List<Integer> player1, final List<Integer> player2);
}
