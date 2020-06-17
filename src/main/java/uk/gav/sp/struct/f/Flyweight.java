package uk.gav.sp.struct.f;

import java.util.HashMap;
import java.util.Map;

public class Flyweight {

	public static class ColouredRectangleFactory {
		private static ColouredRectangleFactory _instance = new ColouredRectangleFactory();
		
		private Map<String, ShapeSize> _cache = new HashMap<>();
		
		public static ColouredRectangleFactory getInstance() {
			return _instance;
		}
		
		private ColouredRectangleFactory() {}
		
		public Rectangle create(final int l, final int b, final Colour colour, final int rotation) {
			String key = RectangleShapeSize.getKey("Rectangle", l, b);
			ShapeSize ss = _cache.get(key);
			
			if (ss == null) {
				ss = new RectangleShapeSize(l, b);
				_cache.put(key, ss);
				System.out.println("FACTORY: Added new shape template to cache");
			}
			else {
				System.out.println("FACTORY: Re-using existing shape template");
			}
			
			return new Rectangle(colour, rotation, ss);
		}
		
	}
	
	public static interface ShapeSize {
		public double getSize();
		public String getDescription();
	}
	
	private static class RectangleShapeSize implements ShapeSize {
		private final int l, b;
		private final double size;
		
		public final static String getKey(final String name, final int l, final int b) {
			return name + "*" + l + "*" + b;
		}
		
		private RectangleShapeSize(final int l, final int b) {
			this.l = l;
			this.b = b;
			System.out.println("Calculating area of rectangle - VERY CPU intensive!");
			this.size = this.l * this.b;

		}
		
		@Override
		public double getSize() {
			return this.size;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return RectangleShapeSize.getKey("Rectangle", this.l, this.b);
		}
	}
		
	public static class Rectangle {
		private Colour 		colour;
		private int    		rotation;
		private ShapeSize	shapeSize;
		
		private Rectangle(final Colour colour, final int rotation, final ShapeSize shapeSize) {
			this.colour = colour;
			this.rotation = rotation;
			this.shapeSize = shapeSize;
			System.out.println("Created the rectangle of size: " + this.shapeSize.getSize());
		}

		public Colour getColour() {
			return colour;
		}

		public void setColour(Colour colour) {
			this.colour = colour;
		}

		public int getRotation() {
			return rotation;
		}

		public void setRotation(int rotation) {
			this.rotation = rotation;
		}

		public ShapeSize getShapeSize() {
			return shapeSize;
		}
	}
	
	protected static enum Colour {
		RED,
		BLUE,
		TRANSPARENT;
	}
}
