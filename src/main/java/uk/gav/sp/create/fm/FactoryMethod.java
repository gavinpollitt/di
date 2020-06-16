package uk.gav.sp.create.fm;

public class FactoryMethod {
	
	public abstract static class ShapePrinter {
		public abstract Shape createShape();
		
		public void printShape() {
			Shape s = this.createShape();
			System.out.print("Rendering a " + s.getDescription());
		}	
	}
	
	public static interface Shape {
		public String getDescription();
	}
	
	public static class CubePrinter extends ShapePrinter {
		public Shape createShape() {
			return new Cube();
		}
	}
	
	public static class SpherePrinter extends ShapePrinter {
		public Shape createShape() {
			return new Sphere();
		}
	}

	
	public static class Sphere implements Shape {
		private final static Colour COLOUR = Colour.RED;
		
		public String getDescription() {
			return "a " + COLOUR + " Sphere";
		}
	}
	
	public static class Cube implements Shape {
		private final static Colour COLOUR = Colour.BLUE;
		
		public String getDescription() {
			return "a " + COLOUR + " cube";
		}
	}
	
	private static enum Colour {
		RED,
		BLUE;
	}

}
