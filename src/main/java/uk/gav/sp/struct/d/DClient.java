package uk.gav.sp.struct.d;

import java.util.ArrayList;
import java.util.List;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.d.Decorator.BirthdayDecorator;
import uk.gav.sp.struct.d.Decorator.ChristmasDecorator;
import uk.gav.sp.struct.d.Decorator.Circle;
import uk.gav.sp.struct.d.Decorator.Colour;
import uk.gav.sp.struct.d.Decorator.Rectangle;
import uk.gav.sp.struct.d.Decorator.Shape;
import uk.gav.sp.struct.d.Decorator.ShapePrinter;

/**
 * 
 * @author regen 
 * Utilising decorators to extend the capability dynamically of existing shapes without affecting the 
 * shape object itself.
 */
public class DClient extends ClientApplication {

	public static void main(String[] args) throws Exception {
		ClientApplication.run(DClient.class);
	}

	/*
	 * Perform to print the original objects and decorated versions
	 */
	@Override
	public void clientCode() {
		List<Shape> shapes = new ArrayList<>();
		
		Shape greenRect = new Rectangle(Colour.GREEN);
		shapes.add(greenRect);
		
		Shape blueCircle = new Circle(Colour.BLUE);
		shapes.add(blueCircle);
		
		Shape ChrisGreenRect = new ChristmasDecorator(greenRect);
		shapes.add(ChrisGreenRect);

		Shape BirthBlueCircle = new BirthdayDecorator(blueCircle);
		shapes.add(BirthBlueCircle);
		
		Shape WorksBlueCircle = new BirthdayDecorator(ChrisGreenRect);
		shapes.add(WorksBlueCircle);
		
		new ShapePrinter().printShapes(shapes);
	}
	
	
}
