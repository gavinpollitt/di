package uk.gav.sp.behaviour.cor;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.Colour;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.ColourBuilder;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.Material;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.MaterialBuilder;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.Packing;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.PackingBuilder;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.Shape;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.ShapeBuilder;
import uk.gav.sp.behaviour.cor.ChainOfResponsibility.ShapePattern;

/**
 * 
 * @author regen
 * Supply a 'recipe' for a shape and pass through a 'supply' chain to construct, colour and pack the shape for sending
 *
 */
public class CORClient extends ClientApplication {
	
	public static void main(String[] args) throws Exception {
		ClientApplication.run(CORClient.class);
	}
	
	/*
	 * Perform the client code - create the pipeline and then send shapes through it.
	 */
	public void clientCode() throws Exception {
		ShapeBuilder sb1 = new MaterialBuilder();
		ShapeBuilder sb2 = new ColourBuilder();
		ShapeBuilder sb3 = new PackingBuilder();
		sb1.setNext(sb2);
		sb2.setNext(sb3);
		clientSphere(sb1);
		clientCube(sb1);
	}
	
	private void clientSphere(final ShapeBuilder pipeline) {
		Shape sphere = new Shape("Sphere");
		ShapePattern pattern = new ShapePattern();
		pattern.setPacking(Packing.BUBBLEWRAP);
		pipeline.build(sphere, pattern);
		System.out.println(sphere.getDescription());
	}
	
	private void clientCube(final ShapeBuilder pipeline) {
		Shape cube = new Shape("Cube");
		ShapePattern pattern = new ShapePattern();
		pattern.setPacking(Packing.BOX);
		pattern.setMaterial(Material.METAL);
		pattern.setColour(Colour.BLUE);
		pattern.setPacking(Packing.BUBBLEWRAP);
		pipeline.build(cube, pattern);
		System.out.println(cube.getDescription());
	}
	

}
