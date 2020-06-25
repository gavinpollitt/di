package uk.gav.sp.behaviour.ob;

import java.util.Arrays;
import java.util.List;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.behaviour.ob.Observer.MaxShape;
import uk.gav.sp.behaviour.ob.Observer.ShapeBag;
import uk.gav.sp.behaviour.ob.Observer.ShapeEventSubscriber;
import uk.gav.sp.behaviour.ob.Observer.ThreeDShape;
import uk.gav.sp.behaviour.ob.Observer.TotalShapeSizer;

/**
 * 
 * @author regen
 * Create a couple of bags of shapes with a common set of event listeners. One listener keeps tabs of the grand total
 * of shapes and the other the maximum shape that's appeared in either bag.
 *
 */
public class OBClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(OBClient.class);
	}
	
	/*
	 * Perform the client code using the appropriate interfaces in lieu of any concrete classes.
	 */
	public void clientCode() throws Exception {
		TotalShapeSizer sub1 = new TotalShapeSizer();
		MaxShape sub2 = new MaxShape();
		List<ShapeEventSubscriber> subs = Arrays.asList(sub1, sub2);
		ShapeBag bag1 = createBag(subs);
		ShapeBag bag2 = createBag(subs);
		
		ThreeDShape bigShape = new ThreeDShape(40.0);
		bag1.addShape(new ThreeDShape(10.0));
		bag1.addShape(new ThreeDShape(20.0));
		bag1.addShape(bigShape);
		bag1.addShape(new ThreeDShape(30.0));
		bag2.addShape(new ThreeDShape(5));
		bag2.addShape(new ThreeDShape(10));
		bag2.addShape(new ThreeDShape(15));
		
		System.out.println("Initial total size:" + sub1.getTotal());
		System.out.println("Initial max shape:" + sub2.getMax());
		
		bag1.removeShape(bigShape);
		
		System.out.println("After removal total size:" + sub1.getTotal());
		System.out.println("After removal max shape:" + sub2.getMax());

	}
	
	private ShapeBag createBag(List<ShapeEventSubscriber> subscribers) {
		final ShapeBag bag = new ShapeBag();
		subscribers.stream().forEach(s -> bag.addSubscriber(s));
		return bag;
	}

}
