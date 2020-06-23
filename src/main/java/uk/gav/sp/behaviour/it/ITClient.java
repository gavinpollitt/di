package uk.gav.sp.behaviour.it;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.behaviour.it.Iterator.Circle;
import uk.gav.sp.behaviour.it.Iterator.Cube;
import uk.gav.sp.behaviour.it.Iterator.ShapeBag;
import uk.gav.sp.behaviour.it.Iterator.Sphere;
import uk.gav.sp.behaviour.it.Iterator.Square;
import uk.gav.sp.behaviour.it.Iterator.TwoDShape;
import uk.gav.sp.behaviour.it.Iterator.ShapeBag.ShapeIterator;

/**
 * 
 * @author regen
 * Create a bag of shapes and allow iteration based on a type. The client has not reference to the implementation
 * class of the iterator
 */
public class ITClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(ITClient.class);
	}
	
	/*
	 * Perform the client code - create the pipeline and then send shapes through it.
	 */
	public void clientCode() throws Exception {
		ShapeBag bag = new ShapeBag();
		
		bag.add(new Sphere());
		bag.add(new Square());
		bag.add(new Circle());
		bag.add(new Cube());
		bag.add(new Sphere());
		bag.add(new Circle());
		
		ShapeIterator si = null;
		System.out.println("Only Spheres...");
		si = bag.iterator(Sphere.class);
		dumpContents(si);
		
		System.out.println("Only 2D shapes...");
		si = bag.iterator(TwoDShape.class);
		dumpContents(si);
		
		System.out.println("All shapes...");
		si = bag.iterator();
		dumpContents(si);
	}
	
	public void dumpContents(final ShapeIterator iterator) {
		String output = "";
		String sep = "";
		while (iterator.hasNext()) {
			output += (sep + iterator.next().getDescription());
			sep = ",";
		}
		System.out.println(output);
	}
	

}
