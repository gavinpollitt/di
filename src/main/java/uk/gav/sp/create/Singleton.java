package uk.gav.sp.create;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singleton {

	public static void main(String[] args) {
		System.out.println("Client code 1-->");
		Singleton s = new Singleton();
		s.clientCode1();
		System.out.println("-------------------------------------");
		
		System.out.println("Client code 2-->");
		s = new Singleton();
		s.clientCode2();
		System.out.println("-------------------------------------");
	}
	
	public void clientCode1() {
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
	
	public void clientCode2() {
		ShapeManager.getInstance().addShape(new Circle(10, Colour.RED));
		ShapeManager.getInstance().addShape(new Circle(25, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Square(6, Colour.BLUE));
		ShapeManager.getInstance().addShape(new Circle(1, Colour.BLUE));
		
		System.out.println(ShapeManager.getInstance());
	}

	public static class ShapeManager {
		private static ShapeManager _instance = new ShapeManager();
		
		private final Map<String,List<Shape>> shapes = new HashMap<>();
		
		public static ShapeManager getInstance() {
			return _instance;
		}		
		
		private ShapeManager() {}
		
		public void addShape(final Shape s) {
			String name = s.getClass().getSimpleName();
			
			List<Shape> sList = shapes.get(name);
			
			if (sList == null) {
				sList = new ArrayList<>();
				shapes.put(name, sList);
			}
			sList.add(s);
		}
		
		public String toString() {
			String output = conc("","Currently maintained list of shapes:");
			for (String name:this.shapes.keySet()) {
				output = conc(output,"Shape:" + name);
				output = this.shapes.get(name).stream().map(s -> s.getDescription()).reduce(output, ShapeManager::conc);
			}
			return output;
		}
		
		private static String conc(final String output, final String input ) {
			return output + input + "\n";
		}
	}
	
	public static interface Shape {
		public String getDescription();
	}

	public static class Square implements Shape {

		private final long size;
		private final Colour colour;
		
		public Square(final int s, final Colour colour) {
			this.size = s*s;
			this.colour = colour;
		}
		
		@Override
		public String getDescription() {
			return "A square of size " + this.size + " units squared in colour " + this.colour;
		}		
	}
	
	public static class Circle implements Shape {

		private final long size;
		private final Colour colour;
		
		public Circle(final int r, final Colour colour) {
			this.size = Math.round(Math.PI * r * r);
			this.colour = colour;
		}
		
		@Override
		public String getDescription() {
			return "A circle of size " + this.size + " units squared in colour " + this.colour;
		}		
	}
	
	private static enum Colour {
		RED,
		BLUE;
	}
}
