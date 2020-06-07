package uk.gav.sp.create;

public class Builder {
	
	public static void main(String[] args) {
		Builder b = new Builder();
		b.clientCode();
	}
	
	public void clientCode() {
		ShapeBuilder builder = new ShapeBuilder(10,50);
		RectangleShape ss = builder.colour(Colour.BLUE).build();
		System.out.println(ss);
		
		System.out.println("-----------");
		
		builder = new ShapeBuilder(5,10);
		ss = builder.depth(20).rotation(90d).colour(Colour.RED).material(Material.CARDBOARD).build();
		System.out.println(ss);
		
		System.out.println("-----------");
		
		builder = new ShapeBuilder(0,10);		
		try {
			ss = builder.build();
		}
		catch (Exception e) {
			System.out.println("Exception found:" + e);
		}
		
		builder = new ShapeBuilder(5,100);		
		try {
			ss = builder.rotation(45).material(Material.METAL).build();
		}
		catch (Exception e) {
			System.out.println("Exception found:" + e);
		}
	}
	
	public static class RectangleShape {
		private Integer l;
		private Integer b;
		private Integer d;
		private Colour colour;
		private Material material;
		private double rotation;
		private long size;

		//private Shape(final int l, final int b)
		//private Shape(final int l, final int b, final int d)
		//private Shape(final int l, final int b, final int d, final Colour colour, final double rotation)
		//private Shape(final int l, final int b,final Colour colour, final double rotation)
		//...etc

		private RectangleShape(final ShapeBuilder builder) {
			this.l = builder.l;
			this.b = builder.b;
			this.d = builder.d;
			this.colour = builder.colour;
			this.material = builder.material;
			this.rotation = builder.rotation;
			this.size = this.l * this.b * (this.d == null?1:this.d);
		}

		public String toString() {
			String output = "";
			output += "Shape dimensions l:" + this.l + ",b:" + this.b + (this.d==null?"":",d:" + this.d) + "\n";
			output += "Size:" + this.size + " units " + (this.d == null?"squared":"cubed") + "\n";
			output += (this.colour==null||this.colour==Colour.TRANSPARENT?"":"Colour:"+this.colour + "\n");
			output += "Material:" + this.material + "\n";
			output += "Rotation:" + this.rotation + " degrees";
			return output;
		}
		
		public int getL() {
			return l;
		}

		public int getB() {
			return b;
		}

		public int getD() {
			return d;
		}

		public long getSize() {
			return this.size;
		}
		
		public Colour getColour() {
			return colour;
		}

		public Material getMaterial() {
			return material;
		}

		public double getRotation() {
			return rotation;
		}

	}
	
	public static class ShapeBuilder {

		private Integer l;
		private Integer b;
		private Integer d;
		private Colour colour  = Colour.TRANSPARENT;
		private Material material  = Material.PLASTIC;
		private double rotation = 0d;
		
		public ShapeBuilder(final int l, final int b) {
			this.l = l;
			this.b = b;
		}
		
		public ShapeBuilder depth(final int d) {
			this.d = d;
			return this;
		}
		
		public ShapeBuilder colour(final Colour colour) {
			this.colour = colour;
			return this;
		}
		
		public ShapeBuilder material(final Material material) {
			this.material = material;
			return this;
		}
		
		public ShapeBuilder rotation(final double rotation) {
			this.rotation = rotation;
			return this;
		}

		public void verifyShape() {
			if ((this.d != null && this.d == 0) || this.l == 0 || this.b == 0) {
				throw new IllegalArgumentException("Dimensions must be non-zero");				
			}
			else if (this.colour == Colour.TRANSPARENT && this.material != Material.PLASTIC) {
				throw new IllegalArgumentException("Transparent shapes can only be made of plastic");
			}
		}
		
 		public RectangleShape build() {
			verifyShape();
			return new RectangleShape(this);
		}
	}
	
	private static enum Colour {
		RED,
		BLUE,
		TRANSPARENT;
	}
	
	private static enum Material {
		METAL,
		PLASTIC,
		CARDBOARD;
	}
}
