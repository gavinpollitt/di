package uk.gav.sp.behaviour.s;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.behaviour.s.Strategy.LocalCoster;
import uk.gav.sp.behaviour.s.Strategy.NationalCoster;
import uk.gav.sp.behaviour.s.Strategy.OverseasCoster;
import uk.gav.sp.behaviour.s.Strategy.Shape;
import uk.gav.sp.behaviour.s.Strategy.ShippingCoster;

/**
 * 
 * @author regen
 * Allow a pack of shapes to be created, but due to the complexity of the package managing its own state, delegate
 * to individual state objects that are only responsible for the logic in their state preventing the need for 
 * convoluted conditional logic in the package itself.
 *
 */
public class StrategyClient extends ClientApplication {
	
	private final static Map<String, ShippingCoster> costers = new HashMap<>();  

	static {
		costers.put("LOCAL", new LocalCoster());
		costers.put("NATIONAL", new NationalCoster());
		costers.put("OVERSEAS", new OverseasCoster());
	}

	public static void main(String[] args) throws Exception {
		ClientApplication.run(StrategyClient.class);
	}
	
	/*
	 * Perform the client code using the without direct state management
	 */
	public void clientCode() {
		List<Shape> shapes = new ArrayList<>();
		shapes.add(new Shape(5.5, 2.5));
		shapes.add(new Shape(10.75, 15));
		shapes.add(new Shape(1.0, 1.5));

		System.out.println("Local Shipping:");
		System.out.println(this.calculateShipping(shapes, costers.get("LOCAL")));
		System.out.println();

		System.out.println("National Shipping:");
		System.out.println(this.calculateShipping(shapes, costers.get("NATIONAL")));
		System.out.println();

		System.out.println("Overseas Shipping:");
		System.out.println(this.calculateShipping(shapes, costers.get("OVERSEAS")));
		System.out.println();

	}

	private String calculateShipping(final List<Shape> shapes, final ShippingCoster coster) {
		double total = coster.calculate(shapes);
		total = Math.round(total/100);
		return "The total cost for shipping " + shapes + " is £" + String.format("%3.2f",total);
	}
	


}
