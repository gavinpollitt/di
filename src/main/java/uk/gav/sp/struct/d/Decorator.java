package uk.gav.sp.struct.d;

import java.util.List;

public class Decorator {
	
	public static class ShapePrinter {
		public void printShapes(final List<Shape> shapes) {
			System.out.println("These are the current print templates on the queue:");
			shapes.stream().map(s -> s.getPrintTemplate()).forEach(System.out::println);
		}
	}
	
	public static interface Shape {
		public Colour getColour();
		public String getPrintTemplate();
	}
	
	public static abstract class ShapeDecorator implements Shape {
		private Shape shape;
		
		private ShapeDecorator() {}
		
		public ShapeDecorator(final Shape s) {
			this.shape = s;
		}
		
		@Override
		public abstract String getPrintTemplate();
		
		protected Shape getShape() {
			return this.shape;
		}
		
		@Override
		public Colour getColour() {
			return this.shape.getColour();
		}		
	}
	
	public static class ChristmasDecorator extends ShapeDecorator {

		public ChristmasDecorator(final Shape s) {
			super(s);
		}
		
		@Override
		public String getPrintTemplate() {
			return this.getShape().getPrintTemplate() + " and with lots of golden Christmas trees";
		}		
	}
	
	public static class BirthdayDecorator extends ShapeDecorator {

		public BirthdayDecorator(final Shape s) {
			super(s);
		}

		@Override
		public String getPrintTemplate() {
			return this.getShape().getPrintTemplate() + " and with lots of silver balloons";
		}		
	}
	
	public static class Rectangle implements Shape {
		private Colour colour;
		
		public Rectangle(final Colour colour) {
			this.colour = colour;
		}
		
		public Colour getColour() {
			return this.colour;
		}
		
		public String getPrintTemplate() {
			return "A " + this.colour + " " + this.getClass().getSimpleName();
		}
	}
	
	public static class Circle implements Shape {
		private Colour colour;
		
		public Circle(final Colour colour) {
			this.colour = colour;
		}
		
		public Colour getColour() {
			return this.colour;
		}
		
		public String getPrintTemplate() {
			return "A " + this.colour + " " + this.getClass().getSimpleName();
		}

	}
	
	protected static enum Colour {
		RED,
		BLUE,
		GREEN;
	}


}
