package uk.gav.sp.create.af;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.create.af.AbstractFactory.Shape;
import uk.gav.sp.create.af.AbstractFactory.ShapeFactory;

/**
 * 
 * @author regen
 * Create a set of shapes of a certain colour determined by an appropriate environment variable.
 * The AbstractFactoryPattern is used ensuring that the client code has no reference to the 
 * concrete factory being used.
 *
 */
public class AFClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(AFClient.class);
	}
	
	/*
	 * Perform the client code using the appropriate interfaces in lieu of any concrete classes.
	 */
	public void clientCode() throws Exception {
		ShapeFactory sf = ShapeFactory.getInstance();
		Shape triangle = sf.createTriangle();
		Shape circle = sf.createCircle();
		System.out.println(triangle.getDescription());
		System.out.println(circle.getDescription());
	}
	

}
