package uk.gav.sp.create.af;

public class AbstractFactory {
	// The environment variable that determines the factory to use
	private static Colour envColour = Colour.RED;

	// ---------------------- Interface section that the client can see ----------------------------------------------------------------------

	public static interface Shape {
		String getDescription();
	}
	
	public static interface Triangle extends Shape {	
	}
	
	public static interface Circle extends Shape {	
	}

	/*
	 * Determine the shape factory from the environment and dynamically load based on this.
	 */
	public static interface ShapeFactory {
		public Shape createTriangle();
		public Shape createCircle();

		public static ShapeFactory getInstance() throws Exception {
			String c = envColour.name().charAt(0) + envColour.name().substring(1).toLowerCase();
			ShapeFactory sf = (ShapeFactory)Class.forName("uk.gav.sp.create.af.AbstractFactory$" + c + "ShapeFactory").getDeclaredConstructor().newInstance();
			return sf;
		}		
	}
	
	// ---------------------- Concrete class section that is hidden from the client ----------------------------------------------------------------------

	public static class RedShapeFactory implements ShapeFactory {
		public Shape createTriangle() {
			return new RedTriangle();
		}
		
		public Shape createCircle() {
			return new RedCircle();
		}

	}

	public static class BlueShapeFactory implements ShapeFactory {
		public Shape createTriangle() {
			return new BlueTriangle();
		}
		
		public Shape createCircle() {
			return new BlueCircle();
		}
	}
	
	public static class RedTriangle implements Triangle {
		public String getDescription() {
			return "I'm a red triangle";
		}
	}
	
	public static class BlueTriangle implements Triangle {
		public String getDescription() {
			return "I'm a blue triangle";
		}
	}
	
	public static class RedCircle implements Circle {
		public String getDescription() {
			return "I'm a red circle";
		}
	}
	
	public static class BlueCircle implements Circle {
		public String getDescription() {
			return "I'm a blue circle";
		}
	}	
	private static enum Colour {
		RED,
		BLUE;
	}
}
