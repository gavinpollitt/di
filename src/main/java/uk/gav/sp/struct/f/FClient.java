package uk.gav.sp.struct.f;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.f.Flyweight.Colour;
import uk.gav.sp.struct.f.Flyweight.ColouredRectangleFactory;


/**
 * 
 * @author regen 
 * Utilising a factory, return Rectangle objects to the consumer. However, as the creation of a base rectangle can prove
 * VERY CPU INTENSIVE, shapes of each size will be cached and reused as the intrinsic object for each new extrinsic object.
 * Only extrinsic object data such as colour and rotation may be adjusted by the client.
 */
public class FClient extends ClientApplication {

	public static void main(String[] args) throws Exception {
		ClientApplication.run(FClient.class);
	}

	/*
	 * Perform the client code to create the objects via the factory.
	 */
	@Override
	public void clientCode() {
		ColouredRectangleFactory factory = ColouredRectangleFactory.getInstance();
		
		factory.create(10, 10, Colour.RED, 45);
		factory.create(20, 10, Colour.BLUE, 0);
		factory.create(10, 10, Colour.BLUE, 90);
		factory.create(10, 10, Colour.TRANSPARENT, 45);
		factory.create(4, 6, Colour.BLUE, 0);
		factory.create(6, 4, Colour.RED, 75);
		factory.create(4, 6, Colour.TRANSPARENT, 0);
		factory.create(20, 10, Colour.RED, 10);
		factory.create(4, 6, Colour.BLUE, 0);
	}
	
	
}
