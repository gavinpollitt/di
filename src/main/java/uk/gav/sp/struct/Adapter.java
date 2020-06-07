package uk.gav.sp.struct;

public class Adapter {

	public static void main(String[] args) {
		Adapter a = new Adapter();
		a.clientCode1();
		a.clientCode2();
	}
	
	public void clientCode1() {
		CircularHole hole = new CircularHole(5);
		
		hole.checkFit(new Circle(4));
		hole.checkFit(new Sphere(5));
		hole.checkFit(new Circle(6));
	}
	
	public void clientCode2() {
		CircularHole hole = new CircularHole(10);
		
		hole.checkFit(new Circle(5));
		hole.checkFit(new Sphere(6));
		hole.checkFit(new Circle(7));
		hole.checkFit(new SquareAdapter(new Square(14)));
		hole.checkFit(new SquareAdapter(new Square(15)));
	}

	public static class CircularHole {
		private double radius;
		
		public CircularHole(final double r) {
			this.radius = r;
		}
		
		public void checkFit(final CircularShape shape) {
			if (this.radius >= shape.getRadius()) {
				System.out.println("This hole of radius:" + this.radius + " will accommodate a " + shape.getDescription());
			}
			else {
				System.out.println("This hole of radius:" + this.radius + " is too small to accommodate a " + shape.getDescription());				
			}
		}
	}
	
	public static interface Shape {
		public String getDescription();
	}
	
	public static interface CircularShape extends Shape {
		public double getRadius();
	}
	
	public static class Circle implements CircularShape {

		final double radius;
		
		public Circle(final double r) {
			this.radius = r;
		}
		
		@Override
		public double getRadius() {
			return this.radius;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Circle of radius " + this.getRadius() ;
		}		
	}
	
	public static class Sphere implements CircularShape {

		final double radius;
		
		public Sphere(final double r) {
			this.radius = r;
		}
		
		@Override
		public double getRadius() {
			return this.radius;
		}

		@Override
		public String getDescription() {
			return "Sphere of radius " + this.getRadius() ;
		}		
	}
		
	public static class Square implements Shape {

		final int side;
		
		public Square(final int s) {
			this.side = s;
		}
		
		public int getSide() {
			return this.side;
		}		
		
		@Override
		public String getDescription() {
			return "Square of radius " + this.getSide() ;
		}		
	}
	
	public static class SquareAdapter implements CircularShape {
		private Square square;

		public SquareAdapter(final Square s) {
			this.square = s;
		}
		
		@Override
		public String getDescription() {
			return "Square with an equivalent radius of: " + this.getRadius();
		}

		@Override
		public double getRadius() {
			// TODO Auto-generated method stub
			return this.square.getSide() * Math.sqrt(2.0)/2;
		}
		
	}

}
