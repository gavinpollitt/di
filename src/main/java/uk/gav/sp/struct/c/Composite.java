package uk.gav.sp.struct.c;

import java.util.ArrayList;
import java.util.List;

public class Composite {

	public static interface ThreeDShape {
		public long getSize();
		public double getCost();
	}
		
	public static class Cuboid implements ThreeDShape {

		private double cost;
		private long size;
		
		public Cuboid(final int l, final int b, final int d) {
			this.size = l * b * d;
			this.cost = Math.round(this.size * 1.5 * 100.0)/100.0;
		}
		
		@Override
		public long getSize() {
			return this.size;
		}
		

		@Override
		public double getCost() {
			return this.cost;
		}
		
		public String toString() {
			return "Cuboid";
		}
	}
	
	
	public static class Cone implements ThreeDShape {
		
		private double cost;
		private long size;
		
		public Cone(final int r, final int h) {
			this.size = Math.round(Math.PI * r * r * h / 3); 
			this.cost = Math.round(this.size * 3 * 100.0)/100.0;
		}
		
		@Override
		public long getSize() {
			return this.size;
		}
		
		@Override
		public double getCost() {
			return this.cost;
		}
		
		public String toString() {
			return "Cone";
		}
	}
	
	public static class ShapeBox implements ThreeDShape {
		private List<ThreeDShape> shapes = new ArrayList<>();
		
		public void addShape(final ThreeDShape s) {			
			shapes.add(s);
		}
		
		@Override
		public long getSize() {
			return shapes.stream().map(s -> s.getSize()).reduce(0L, (a,v) -> a+v);
		}

		@Override
		public double getCost() {
			return shapes.stream().map(s -> s.getCost()).reduce(0.0d, (a,v) -> a+v);
		}
		
		public String toString() {
			return "Box contains " + shapes;
		}
	}

}
