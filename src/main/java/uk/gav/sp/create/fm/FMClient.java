package uk.gav.sp.create.fm;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.create.fm.FactoryMethod.CubePrinter;
import uk.gav.sp.create.fm.FactoryMethod.ShapePrinter;
import uk.gav.sp.create.fm.FactoryMethod.SpherePrinter;

/**
 * 
 * @author regen
 * Print a cube or a sphere without the specific client code knowing what type of shape is being printed
 *
 */
public class FMClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(FMClient.class);
	}
	
	/*
	 * Perform the client code using the appropriate interfaces in lieu of any concrete classes.
	 */
	public void clientCode() {
		this.clientCode(new CubePrinter());
		this.clientCode(new SpherePrinter());
	}
	
	private void clientCode(final ShapePrinter sp) {
		System.out.println("Starting a print cycle");
		sp.printShape();
		System.out.println("\nPrinting complete\n");
	}
}
