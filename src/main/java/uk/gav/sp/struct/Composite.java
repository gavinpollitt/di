package uk.gav.sp.struct;

import java.util.ArrayList;
import java.util.List;

public class Composite {

	public static void main(String[] args) {
		Composite c = new Composite();
		c.clientCode();
	}
	
	public void clientCode() {
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

	}
	
	public static interface ThreeDShape {
		public long getSize();
		public double getCost();
	}
		
	public static class Cuboid implements ThreeDShape {

		private double cost;
		private long size;
		
		public Cuboid(final int l, final int b, final int d) {
			this.size = l * b * d;
			this.cost = Math.round(this.size * 1.5 * 100)/100;
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
			this.cost = Math.round(this.size * 3 * 100)/100;
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
