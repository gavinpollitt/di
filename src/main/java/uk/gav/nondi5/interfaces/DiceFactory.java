package uk.gav.nondi5.interfaces;

import java.util.List;

public interface DiceFactory {
	public List<Die> create();
	public int getSides();
	public int getDice();
}
