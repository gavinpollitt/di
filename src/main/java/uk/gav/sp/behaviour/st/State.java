package uk.gav.sp.behaviour.st;

import java.util.Stack;

public class State {

	public static class Shape {
		private final double size;
		
		public Shape(final double size) {
			this.size = size;
		}

		public double getSize() {
			return this.size;
		}
		
		public String toString() {
			return "A shape of size: " + this.size;
		}		
	}
	

	public static class ShapePackage {
		private PackageState state;
		private Stack<Shape> contents = new Stack<>();
		private final double maxSize;
		private Wrapping wrapping;
		
		public ShapePackage(final double maxSize) {
			this.state = new Empty(this);
			this.maxSize = maxSize;
			System.out.println(this.stateOutput("Created package of maximum size:" + this.maxSize));
		}
		
		public String addShape(final Shape s)  {
			return "add() --> " + checkComplete(this.stateOutput(state.add(s)));
		}
		
		public String popShape()  {
			return "pop() --> " + checkComplete(this.stateOutput(state.pop()));
		}

		public String sealPackage() {
			return "seal() --> " + checkComplete(this.stateOutput(state.seal()));
		}
		
		public String wrapPackage(final Wrapping w) {
			return "wrap() --> " + checkComplete(this.stateOutput(state.wrap(w)));
		}
		
		public boolean isComplete() {
			return this.state.isComplete();
		}
		
		public String toString() {
			String output = "";
			if (this.isComplete()) {
				String so = "The package is complete and wrapped in " + this.wrapping + " paper and contains:\n";
				output += this.contents.stream().map(s -> s.toString()).reduce(so, (a,v) -> a.concat(v + "\n"));
			}
			else {
				output += "The packaging process is still underway";
			}
			
			return output;
		}
		
		private double currentSize() {
			return contents.stream().map(s -> s.size).reduce(0d,(a,v) -> a+v);
		}
		
		private String stateOutput(final String m) {
			return "(" + this.state.getClass().getSimpleName() + ")-" + m;  
		}
		
		private String checkComplete(final String message) {
			if (isComplete()) {
				return "Package is ready for sending - no more actions allowed";
			}
			else {
				return message;
			}
		}
		
	}
	
	private static abstract class PackageState {
		protected ShapePackage shapePackage;
		
		private PackageState() {
		}
		
		protected PackageState(final ShapePackage shapePackage) {
			this();
			this.shapePackage = shapePackage;
		}
		
		protected String add(final Shape s)  {
			return defaultException("Cannot add a shape to package with state");
		}
		
		protected String pop()  {
			return defaultException("Cannot remove last shape from package with state");
		}

		protected String seal()  {
			return defaultException("Cannot seal package with state");
		}

		protected String wrap(final Wrapping w)  {
			return defaultException("Cannot wrap package with state");
		}
		
		protected boolean isComplete() {
			return false;
		}

		private String defaultException(final String message) {
			return message + ":" + this.getClass().getSimpleName();
		}
	}
	
	private static class Empty extends PackageState {
		public Empty(final ShapePackage sp) {
			super(sp);
		}
		
		public String add(final Shape s)  {
			this.shapePackage.contents.push(s);
			double max = this.shapePackage.maxSize;
			String output = "Successfully added shape: " + s;
			if (s.size > max) {
				//Need to move to FULL state
				output += ", but box is too full by " + s.size;
				this.shapePackage.state = new Full(this.shapePackage);
			}
			else {
				this.shapePackage.state = new InProgress(this.shapePackage);				
			}
			
			return output;
		}
	}
	
	private static class InProgress extends PackageState {
		public InProgress(final ShapePackage sp) {
			super(sp);
		}
		
		public String add(final Shape s) {
			this.shapePackage.contents.push(s);
			double size = this.shapePackage.currentSize();
			double max = this.shapePackage.maxSize;

			
			String output = "Successfully added shape: " + s;
			if (size > max) {
				//Need to move to FULL state
				output += ", but box is too full by " + (size-max);
				this.shapePackage.state = new Full(this.shapePackage);
			}
			return output;
		}

		@Override
		protected String pop() {
			double size = this.shapePackage.contents.size();
			Shape s = this.shapePackage.contents.pop();

			String output = "Successfully removed shape: " + s;
			
			if (size == 1) {
				this.shapePackage.state = new Empty(this.shapePackage);
				output += " and box is now empty"; 
			}
			
			return output;	
		}	
		
		@Override
		protected String seal()  {
			double size = this.shapePackage.currentSize();
			this.shapePackage.state = new Sealed(this.shapePackage);
			
			return "Successfully sealed package of content size: " + size;
		}

	}
	
	private static class Full extends PackageState {
		public Full(final ShapePackage sp) {
			super(sp);
		}

		@Override
		protected String pop() {
			double size = this.shapePackage.currentSize();
			Shape s = this.shapePackage.contents.pop();

			String output = "Successfully removed shape: " + s;
			
			if (size == 1) {
				this.shapePackage.state = new Empty(this.shapePackage);
				output += " and box is now empty"; 
			}
			else {
				this.shapePackage.state = new InProgress(this.shapePackage);
				
			}
			
			return output;
		}
	}
	
	private static class Sealed extends PackageState {
		public Sealed(final ShapePackage sp) {
			super(sp);
		}
		
		@Override
		protected String wrap(final Wrapping w)  {
			String output = "Package wrapped with " + w + " paper";
			
			if (w == Wrapping.GOODLUCK) {
				output = w + " paper has been discontinued, choose an alternative";
			}
			else {
				this.shapePackage.state = new Wrapped(this.shapePackage);
				this.shapePackage.wrapping = w;
			}
			
			return output;
		}
	}
	
	private static class Wrapped extends PackageState {
		public Wrapped(final ShapePackage sp) {
			super(sp);
		}
		
		@Override
		public boolean isComplete() {
			return true;
		}
		
	}
	
	public enum Wrapping {
		BIRTHDAY,
		CHRISTMAS,
		GOODLUCK;
	}
}
