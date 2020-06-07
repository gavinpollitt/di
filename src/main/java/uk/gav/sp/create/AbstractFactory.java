package uk.gav.sp.create;

public class AbstractFactory {
	private static Colour envColour = Colour.RED;

	public static void main(String[] args) throws Exception {
		AbstractFactory af = new AbstractFactory();
		af.clientCode();
	}
	
	public void clientCode() throws Exception {
		ShapeFactory sf = ShapeFactory.getInstance();
		Shape triangle = sf.createTriangle();
		Shape circle = sf.createCircle();
		System.out.println(triangle.getDescription());
		System.out.println(circle.getDescription());
	}
	
	public static interface Shape {
		String getDescription();
	}
	
	public static interface ShapeFactory {
		public Shape createTriangle();
		public Shape createCircle();

		public static ShapeFactory getInstance() throws Exception {
			String c = envColour.name().charAt(0) + envColour.name().substring(1).toLowerCase();
			ShapeFactory sf = (ShapeFactory)Class.forName("uk.gav.sp.create.AbstractFactory$" + c + "ShapeFactory").getDeclaredConstructor().newInstance();
			return sf;
		}		
	}
	
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
	
	public static interface Triangle extends Shape {	
	}
	
	public static interface Circle extends Shape {	
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
