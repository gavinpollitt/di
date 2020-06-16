package uk.gav.sp.create.p;

import java.util.ArrayList;
import java.util.List;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.create.p.Prototype.Colour;
import uk.gav.sp.create.p.Prototype.Cone;
import uk.gav.sp.create.p.Prototype.Cuboid;
import uk.gav.sp.create.p.Prototype.ThreeDShape;

/**
 * 
 * @author regen
 * Create 'cloned' objects based on a concrete objected that was created with the 'heavy lifting' 
 */
public class PClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(PClient.class);
	}
	
	/*
	 * Perform the client code to create the base objects and then use clones for calculations.
	 */
	public void clientCode() throws Exception {
		
		System.out.println("Create the prototype objects");
		
		//Heavyweight process
		ThreeDShape pCuboid = new Cuboid(5, 2, 10, Colour.BLUE);
		ThreeDShape pCone = new Cone(5,10, Colour.RED);
		
		this.clientTotaller(pCuboid, pCone);
		
	}
	
	public void clientTotaller(final ThreeDShape baseCube, final ThreeDShape baseCone) {
		List<ThreeDShape> shapes = new ArrayList<>();

		// Lightweight process
		System.out.println("Add cuboid clones to the list");
		shapes.add(baseCube.clone());
		shapes.add(baseCube.clone());
		shapes.add(baseCube.clone());
		shapes.add(baseCube.clone());

		System.out.println("Add cone clones to the list");
		shapes.add(baseCone.clone());
		shapes.add(baseCone.clone());

		System.out.println("The total size of our shapes is:");
		Long total = shapes.stream().map(s -> s.getSize()).reduce(0l, (v,a) -> v+a);
		System.out.println(total + " units cubed");	
	}
}
