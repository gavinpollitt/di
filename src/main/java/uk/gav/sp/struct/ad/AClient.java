package uk.gav.sp.struct.ad;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.ad.Adapter.Circle;
import uk.gav.sp.struct.ad.Adapter.CircularHole;
import uk.gav.sp.struct.ad.Adapter.Sphere;
import uk.gav.sp.struct.ad.Adapter.Square;
import uk.gav.sp.struct.ad.Adapter.SquareToCircleAdapter;

/**
 * 
 * @author regen
 * Use an adapter to expose a 'radius' on a square object to see if it (and other more conventional 
 * radius-style shapes) will fit in a hole.
 */
public class AClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(AClient.class);
	}
	
	/*
	 * Perform the client code store shapes across the single ShapeManager
	 */
	@Override
	public void clientCode() throws Exception {
		this.clientCode1();
		this.clientCode2();
	}	


	public void clientCode1() {
		CircularHole hole = new CircularHole(5);
		
		hole.checkFit(new Circle(4));
		hole.checkFit(new Sphere(5));
		hole.checkFit(new Circle(6));
	}
	

	public void clientCode2() {
		CircularHole hole = new CircularHole(10);
		
		hole.checkFit(new Circle(5));
		hole.checkFit(new Sphere(6));
		hole.checkFit(new Circle(7));
		hole.checkFit(new SquareToCircleAdapter(new Square(14)));
		hole.checkFit(new SquareToCircleAdapter(new Square(15)));
	}
}
