package uk.gav.sp.struct.c;

import java.util.Arrays;
import java.util.List;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.c.Composite.Cone;
import uk.gav.sp.struct.c.Composite.Cuboid;
import uk.gav.sp.struct.c.Composite.ShapeBox;
import uk.gav.sp.struct.c.Composite.ThreeDShape;


/**
 * 
 * @author regen Create a composite object that can hold many shapes, but offers the same interface
 * as the shapes themselves. This allows a client that requires the total cost or size of a group
 * of individuals shapes and/or composite shapes can use the same methods without necessarily knowing
 * the type of object. 
 */
public class CClient extends ClientApplication {

	public static void main(String[] args) throws Exception {
		ClientApplication.run(CClient.class);
	}

	/*
	 * Perform the client code to create the objects and provide the necessarily metric totals
	 */
	@Override
	public void clientCode() throws Exception {
		ThreeDShape cb1 = new Cuboid(2,2,5);
		ThreeDShape cb2 = new Cuboid(10,2,3);
		ThreeDShape cb3 = new Cuboid(4,4,4);
		ThreeDShape cb4 = new Cuboid(1,1,2);
		ThreeDShape cb5 = new Cuboid(10,9,8);
		
		ThreeDShape cn1 = new Cone(2,5);
		ThreeDShape cn2 = new Cone(4,6);
		ThreeDShape cn3 = new Cone(2,2);
		ThreeDShape cn4 = new Cone(8,5);
		ThreeDShape cn5 = new Cone(7,7);
		
		ShapeBox b1 = new ShapeBox();
		b1.addShape(cb1);
		b1.addShape(cb2);

		ShapeBox b2 = new ShapeBox();
		b2.addShape(cn1);
		b2.addShape(cb3);
		
		ShapeBox b3 = new ShapeBox();
		b3.addShape(b2);
		b3.addShape(cb4);
		b3.addShape(cn2);
		
		ShapeBox b4 = new ShapeBox();
		b4.addShape(b1);
		b4.addShape(b2);
		b4.addShape(cn3);
		b4.addShape(cn4);
		b4.addShape(cn5);
		b4.addShape(cb5);
		
		System.out.println("Main box 1 contains " + b3);
		System.out.println("Total size: " + b3.getSize());
		System.out.println("Total cost: " + b3.getCost());
		System.out.println();
		System.out.println("Main box 2 contains " + b4);
		System.out.println("Total size: " + b4.getSize());
		System.out.println("Total cost: " + b4.getCost());
		
		System.out.println("\nCreate a set of shapes");
		List<ThreeDShape> shapes = Arrays.asList(new ThreeDShape[] {cb1,cb2,cb3,cb4,cb5,cn1,cn2,cn3,cn4,cn5,b1,b2,b3,b4});
		clientShapeTotaller(shapes);
	}
	
	private void clientShapeTotaller(final List<ThreeDShape> shapes) {
		shapes.stream().map(this::shapeDescriptor).forEach(System.out::println);
	}
	
	private String shapeDescriptor(final ThreeDShape shape) {
		return shape.toString() + "--> size:" + shape.getSize() + ", cost:" + shape.getCost(); 
	}

}
