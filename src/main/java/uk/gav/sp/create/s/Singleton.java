package uk.gav.sp.create.s;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Singleton {


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
	
	public static enum Colour {
		RED,
		BLUE;
	}
}
