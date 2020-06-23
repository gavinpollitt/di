package uk.gav.sp.behaviour.cor;

public class ChainOfResponsibility {
	public static abstract class ShapeBuilder {
		private ShapeBuilder next;
		
		public abstract void build(final Shape shape, final ShapePattern pattern);
		
		public void setNext(final ShapeBuilder sb) {
			this.next = sb;
		}
		
		protected final void next(final Shape shape, final ShapePattern pattern) {
			if (this.next != null) {
				this.next.build(shape, pattern);
			}
		}
	}
	
	public static class Shape{
		private String description;
		
		public Shape(final String name) {
			this.description = "Shape:" + name;
			
		}
		
		public String getDescription() {
			return this.description;
		}
		
		public void updateDescription(final String up) {
			this.description+=(" " + up);
		}
	}
	

	public static class ShapePattern {
		private Colour colour;
		private Material material;
		private Packing packing;
		public Colour getColour() {
			return colour;
		}
		
		public void setColour(Colour colour) {
			this.colour = colour;
		}
		public Material getMaterial() {
			return material;
		}
		public void setMaterial(Material material) {
			this.material = material;
		}
		public Packing getPacking() {
			return packing;
		}
		public void setPacking(Packing packing) {
			this.packing = packing;
		}						
	}
	
	public static class MaterialBuilder extends ShapeBuilder {

		@Override
		public void build(final Shape shape, final ShapePattern pattern) {
			Material m = pattern.getMaterial()==null?Material.PLASTIC:pattern.getMaterial();
			shape.updateDescription("Material:" + m);
			this.next(shape, pattern);
		}
	}
	
	public static class ColourBuilder extends ShapeBuilder {

		@Override
		public void build(final Shape shape, final ShapePattern pattern) {
			Colour c = pattern.getColour()==null?Colour.WHITE:pattern.getColour();
			shape.updateDescription("Colour:" + c);
			this.next(shape, pattern);
		}
	}
	
	public static class PackingBuilder extends ShapeBuilder {

		@Override
		public void build(final Shape shape, final ShapePattern pattern) {
			Packing p = pattern.getPacking()==null?Packing.WRAPPING:pattern.getPacking();
			shape.updateDescription("Packing:" + p);
			this.next(shape, pattern);
		}
	}
	
	protected static enum Colour {
		WHITE,
		RED,
		BLUE;
	}
	
	protected static enum Material {
		METAL,
		PLASTIC,
		CARDBOARD;
	}
	
	protected static enum Packing {
		BOX,
		WRAPPING,
		BUBBLEWRAP;
	}
}
