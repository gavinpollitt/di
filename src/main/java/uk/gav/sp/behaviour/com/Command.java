package uk.gav.sp.behaviour.com;

public class Command {
	public static interface PrintCommand {
		public void execute();
	}
	
	public static abstract class Printer {
		protected Colour currentInk;
		protected Pattern currentPattern;
		
		public void mixInk(final Colour c) {
			this.currentInk = c;
		}
		
		public void setPattern(final Pattern p) {
			this.currentPattern = p;			
		}
		
		public abstract void print(final Shape s);
	}
	
	public static abstract class Shape{
		private String description;
		private Colour c;
		
		public Shape(final String name) {
			this.description = "Shape:" + name;
			
		}
		
		public String getDescription() {
			return this.description;
		}		
	}
	
	public static class InkCommand implements PrintCommand {
		private Printer target;
		
		public InkCommand(final Printer t) {
			this.target = t;
		}
		
		@Override
		public void execute() {
		}		
	}
	
	public static class TwoDShape extends Shape {

		public TwoDShape(final String name) {
			super(name);
		}
		
		@Override
		public String getDescription() {
			return "2D" + super.getDescription();
		}	
	}
	
	public static class ThreeDShape extends Shape {

		public ThreeDShape(final String name) {
			super(name);
		}
		
		@Override
		public String getDescription() {
			return "3D" + super.getDescription();
		}	
	}
	
	public static class TwoDPrinter extends Printer {
		public void print(final Shape s) {
			System.out.println("2D Printing " + s.getDescription() + " in colour " + this.currentInk + " and pattern " + this.currentPattern);
		}	
	}
	
	public static class ThreeDPrinter extends Printer {
		public void print(final Shape s) {
			System.out.println("3D Printing " + s.getDescription() + " in colour " + this.currentInk + " and pattern " + this.currentPattern);
		}	
	}
	
	protected static enum Colour {
		WHITE,
		RED,
		BLUE;
	}
	
	protected static enum Pattern {
		STRIPED,
		SPOTTED;
	}
	
}
