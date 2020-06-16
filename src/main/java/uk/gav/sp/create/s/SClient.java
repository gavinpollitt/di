package uk.gav.sp.create.s;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.create.s.Singleton.Circle;
import uk.gav.sp.create.s.Singleton.Colour;
import uk.gav.sp.create.s.Singleton.ShapeManager;
import uk.gav.sp.create.s.Singleton.Square;

/**
 * 
 * @author regen
 * Create a singleton shape manager that will return the same instance of itself across clients
 * to keep a collection of added shapes across all clients.
 */
public class SClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(SClient.class);
	}
	
	/*
	 * Perform the client code store shapes across the single ShapeManager
	 */
	public void clientCode() throws Exception {
			System.out.println("Client 1 code-->");
			client1Code();
			System.out.println("-------------------------------------");
			
			System.out.println("Client 2 code-->");
			client2Code();
			System.out.println("-------------------------------------");
	}
	
	public void client1Code() {
		ShapeManager.getInstance().addShape(new Square(5, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Square(10, Colour.RED));
		ShapeManager.getInstance().addShape(new Circle(2, Colour.RED));
		ShapeManager.getInstance().addShape(new Square(12, Colour.RED));
		ShapeManager.getInstance().addShape(new Square(18, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Circle(5, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Circle(5, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Square(25, Colour.RED));
		
		System.out.println(ShapeManager.getInstance());
	}
	
	public void client2Code() {
		ShapeManager.getInstance().addShape(new Circle(10, Colour.RED));
		ShapeManager.getInstance().addShape(new Circle(25, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Square(6, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Circle(1, Colour.BLUE));
		
		System.out.println(ShapeManager.getInstance());
	}
}
