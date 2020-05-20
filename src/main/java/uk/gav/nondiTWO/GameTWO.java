package uk.gav.nondiTWO;

public class GameTWO {
	public static void main(String[] args) {		
		BasicDiceFactory bdf4x6 = new BasicDiceFactory(6, 4);
		System.out.println(new HighestRollGame(bdf4x6).play());
		System.out.println(new HighestRollGame(bdf4x6).play());
		
		BasicDiceFactory bdf5x6 = new BasicDiceFactory(6, 5);
		System.out.println(new DoubleUpGame(bdf5x6).play());

	}
}
