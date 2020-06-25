package uk.gav.sp.behaviour.st;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.behaviour.st.State.Shape;
import uk.gav.sp.behaviour.st.State.ShapePackage;
import uk.gav.sp.behaviour.st.State.Wrapping;

/**
 * 
 * @author regen
 * Allow a pack of shapes to be created, but due to the complexity of the package managing its own state, delegate
 * to individual state objects that are only responsible for the logic in their state preventing the need for 
 * convoluted conditional logic in the package itself.
 *
 */
public class StateClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(StateClient.class);
	}
	
	/*
	 * Perform the client code using the without direct state management
	 */
	public void clientCode() {
		ShapePackage shapePackage = new ShapePackage(100);
		System.out.println(shapePackage.addShape(new Shape(40)));
		System.out.println(shapePackage.popShape());
		System.out.println(shapePackage.popShape());
		System.out.println(shapePackage.addShape(new Shape(40)));
		System.out.println(shapePackage.addShape(new Shape(30)));
		System.out.println(shapePackage.addShape(new Shape(25)));
		System.out.println(shapePackage.addShape(new Shape(10)));
		System.out.println(shapePackage.sealPackage());
		System.out.println(shapePackage.addShape(new Shape(5)));
		System.out.println(shapePackage.popShape());
		System.out.println(shapePackage.popShape());
		System.out.println(shapePackage.addShape(new Shape(30)));
		System.out.println(shapePackage.sealPackage());
		System.out.println(shapePackage);
		System.out.println(shapePackage.wrapPackage(Wrapping.GOODLUCK));
		System.out.println(shapePackage.wrapPackage(Wrapping.BIRTHDAY));
		System.out.println(shapePackage);
		System.out.println(shapePackage.addShape(new Shape(10)));
		System.out.println(shapePackage.sealPackage());		
	}
	


}
