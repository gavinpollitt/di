package uk.gav.sp.create.p;

public class Prototype {
		
	public static interface ThreeDShape {
		public long getSize();
		public Colour getColour();
		public ThreeDShape clone();		
	}
	
	public static class Cuboid implements ThreeDShape {
		
		private long size;
		private Colour colour;
		
		public Cuboid(final int l, final int b, final int d, Colour colour) {
			this.colour = colour;
			this.size = l * b * d; 
			System.out.println("I've just performed VERY expensive Cuboid size calculation:" + this.size);
		}
		
		private Cuboid(final Cuboid prototype) {
			this.colour = prototype.colour;
			this.size = prototype.size;
			System.out.println("No expensive Cuboid size calculation required");
		}
		
		@Override
		public long getSize() {
			return this.size;
		}

		@Override
		public Colour getColour() {
			return this.colour;
		}
		
		public String toString() {
			return "I am a Cuboid";
		}

		@Override
		public ThreeDShape clone()  {
			return new Cuboid(this);
		}
		
	}
	
	public static class Cone implements ThreeDShape {
		
		private long size;
		private Colour colour;
		
		public Cone(final int r, final int h, Colour colour) {
			this.colour = colour;
			this.size = Math.round(Math.PI * r * r * h / 3); 
			System.out.println("I've just performed VERY expensive Cone size calculation:" + this.size);
		}
		
		private Cone(final Cone prototype) {
			this.colour = prototype.colour;
			this.size = prototype.size;
			System.out.println("No expensive Cone size calculation required");
		}
		
		@Override
		public long getSize() {
			return this.size;
		}

		@Override
		public Colour getColour() {
			return this.colour;
		}
		
		public String toString() {
			return "I am a Cuboid";
		}

		@Override
		public ThreeDShape clone()  {
			return new Cone(this);
		}
		
	}
	
	protected static enum Colour {
		RED,
		BLUE;
	}
}
