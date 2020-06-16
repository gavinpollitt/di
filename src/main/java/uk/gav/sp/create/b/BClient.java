package uk.gav.sp.create.b;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.create.b.Builder.Colour;
import uk.gav.sp.create.b.Builder.Material;
import uk.gav.sp.create.b.Builder.RectangleShape;
import uk.gav.sp.create.b.Builder.ShapeBuilder;

/**
 * 
 * @author regen
 * Control the construction of concrete shape objects where each can have a variety of attributes, some mandatory and
 * some optional. Also, derived attributes such as total size can be derived at creation time as they are immutable.
 * Validation can be included at object build time to ensure appropriate attributes are in place.
 *
 */
public class BClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(BClient.class);
	}
	
	/*
	 * 'Create' our rectangles under ShapeBuilder control. 'new' is not accessible.
	 */
	public void clientCode() {
		
		ShapeBuilder builder = new ShapeBuilder(10,50);
		RectangleShape ss = builder.colour(Colour.BLUE).build();
		System.out.println(ss);
		
		System.out.println("-----------");
		
		builder = new ShapeBuilder(5,10);
		ss = builder.depth(20).rotation(90d).colour(Colour.RED).material(Material.CARDBOARD).build();
		System.out.println(ss);
		
		System.out.println("-----------");
		
		builder = new ShapeBuilder(0,10);		
		try {
			ss = builder.build();
		}
		catch (Exception e) {
			System.out.println("Exception found:" + e);
		}
		
		builder = new ShapeBuilder(5,100);		
		try {
			ss = builder.rotation(45).material(Material.METAL).build();
		}
		catch (Exception e) {
			System.out.println("Exception found:" + e);
		}
	}	

}
