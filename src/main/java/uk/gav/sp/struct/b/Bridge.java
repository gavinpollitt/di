package uk.gav.sp.struct.b;

import java.util.ArrayList;
import java.util.List;

public class Bridge {
	
	public static interface ThreeDShape {
		public double getSize();
		public Colour getColour();
	}
		
	public static interface ShapePackage {
		public double getCost();
		public double getWeight();
		public void addShape(ThreeDShape shape);
		public String getDescription();
	}
	
	public static abstract class ShapePacker {
		protected static final double POSTAGE_RATE = 0.25;
		
		protected final ShapePackage pack;
		
		public ShapePacker(final ShapePackage shapePackage) {
			this.pack = shapePackage;
		}

		public abstract void packAndSend();
		
		public void addShape(final ThreeDShape shape) {
			pack.addShape(shape);
		}
		
		public double getCost() {
			return pack.getCost();
		}
		
		public double getWeight() {
			return pack.getWeight();
		}	
		
		public double getPostage() {
			return Math.round(100.0*this.getWeight() * POSTAGE_RATE)/100.0;
		}
		
	}
	
	public static class BirthdayShapePacker extends ShapePacker {

		public BirthdayShapePacker(ShapePackage shapePackage) {
			super(shapePackage);
		}

		public double packagingCost() {
			return 2.50;
		}
		
		public String packagingType() {
			return "Spangly bows and birthday wrapping paper";
		}
		
		public double getShippingCost() {
			return Math.round(100.0*this.getWeight()*Postage.SECOND_CLASS.getUnitCost())/100.0;
		}
		
		@Override
		public void packAndSend() {
			System.out.println("--------------RECEIPT--------------------");
			System.out.println(this.pack.getDescription());
			System.out.println("WRAPPING:" + this.packagingType());
			System.out.println("ITEM COST:" + this.getCost() + ", PACKAGING COST: " + this.packagingCost());
			System.out.println("WEIGHT:" + this.getWeight());
			System.out.println("SHIPPING:" + this.getShippingCost());
			System.out.println("TOTAL:" + (this.getShippingCost() + this.getCost() + this.packagingCost()));		
			System.out.println("-----------------------------------------\n");
		}
		
	}
	
	public static class AnniversaryShapePacker extends ShapePacker {

		public AnniversaryShapePacker(ShapePackage shapePackage) {
			super(shapePackage);
		}

		public double packagingCost() {
			return 10.0;
		}
		
		public String packagingType() {
			return "Golden rose bows and gold-plated wrapping paper";
		}
		
		public double getShippingCost() {
			return Math.round(100.0*this.getWeight()*Postage.FIRST_CLASS.getUnitCost())/100.0;
		}
		
		@Override
		public void packAndSend() {
			System.out.println("--------------RECEIPT--------------------");
			System.out.println(this.pack.getDescription());
			System.out.println("WRAPPING:" + this.packagingType());
			System.out.println("ITEM COST:" + this.getCost() + ", PACKAGING COST: " + this.packagingCost());
			System.out.println("WEIGHT:" + this.getWeight());
			System.out.println("SHIPPING:" + this.getShippingCost());
			System.out.println("TOTAL:" + (this.getShippingCost() + this.getCost() + this.packagingCost()));		
		}
		
	}
	
	public static class BasicPackage implements ShapePackage {
		private final Material material = Material.PLASTIC;
		private final int margin = 2;
		
		private List<ThreeDShape> shapes = new ArrayList<>();

		@Override
		public double getCost() {
			double cost = shapes.stream().map(s -> s.getSize()*material.getUnitCost()).reduce(0.0d, (a,v) -> a+v);
			cost = Math.round(100.0*(cost + (cost*this.margin/100.0)))/100.0; 
			return cost;
		}

		@Override
		public double getWeight() {
			double weight = shapes.stream().map(s -> s.getSize()*material.getUnitWeight()).reduce(0.0d, (a,v) -> a+v);
			return Math.round(100.0*weight)/100.0; 
		}

		@Override
		public void addShape(final ThreeDShape shape) {
			this.shapes.add(shape);
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return this.getClass().getSimpleName() + " contains " + this.shapes.size() + " shapes made of " + this.material;
		}		
	}
	
	public static class PremierPackage implements ShapePackage {
		private final Material material = Material.METAL;
		private final int margin = 5;
		
		private List<ThreeDShape> shapes = new ArrayList<>();

		@Override
		public double getCost() {
			double cost = shapes.stream().map(s -> s.getSize()*material.getUnitCost()).reduce(0.0d, (a,v) -> a+v);
			cost = Math.round(100.0*(cost + (cost*this.margin/100.0)))/100.0; 
			return cost;
		}

		@Override
		public double getWeight() {
			double weight = shapes.stream().map(s -> s.getSize()*material.getUnitWeight()).reduce(0.0d, (a,v) -> a+v);
			return Math.round(100.0*weight)/100.0; 
		}

		@Override
		public void addShape(final ThreeDShape shape) {
			this.shapes.add(shape);
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return this.getClass().getSimpleName() + " contains " + this.shapes.size() + " shapes made of " + this.material;
		}		
	}
	
	public static class Cuboid implements ThreeDShape {

		private final long size;
		private final Colour colour;
		
		public Cuboid(final int l, final int b, final int d, final Colour colour) {
			this.size = l * b * d;
			this.colour = colour;
		}
		
		@Override
		public double getSize() {
			return this.size;
		}
		
		@Override
		public Colour getColour() {
			// TODO Auto-generated method stub
			return this.colour;
		}

		public String toString() {
			return "Cuboid";
		}
	}
	
	public static class Sphere implements ThreeDShape {

		private double size;
		private final Colour colour;
		
		public Sphere(final int r, final Colour colour) {
			this.size = 4.0/3.0 * Math.PI * r * r * r;
			this.colour = colour;
		}
		
		@Override
		public double getSize() {
			return this.size;
		}
		
		@Override
		public Colour getColour() {
			// TODO Auto-generated method stub
			return this.colour;
		}


		public String toString() {
			return "Sphere";
		}
	}
	
	public static class Cone implements ThreeDShape {
		
		private long size;
		private final Colour colour;
		
		public Cone(final int r, final int h, final Colour colour) {
			this.size = Math.round(Math.PI * r * r * h / 3); 
			this.colour = colour;
		}
		
		@Override
		public double getSize() {
			return this.size;
		}
		
		public String toString() {
			return "Cone";
		}
		
		@Override
		public Colour getColour() {
			// TODO Auto-generated method stub
			return this.colour;
		}

	}
	
	public static enum Postage {
		FIRST_CLASS(0.2),
		SECOND_CLASS(0.1);
		
		private double cost;

		private Postage(final double costPUV) {
			this.cost = costPUV;
		}
		
		public double getUnitCost() {
			return this.cost;
		}
 
	}
	
	public static enum Colour {
		GOLD(2.00),
		RED(1.00),
		BLUE(1.00),
		TRANSPARENT(0.50);
		
		private double cost;

		private Colour(final double costPUV) {
			this.cost = costPUV;
		}
		
		public double getUnitCost() {
			return this.cost;
		}

	}
	
	public static enum Material {
		
		METAL(2.00,5.0),
		PLASTIC(1.00, 0.05),
		GLASS(1.50, 0.5);

		private double cost;
		private double weight;

		private Material(final double costPUV, final double weightPUV) {
			this.cost = costPUV;
			this.weight = weightPUV;
		}
		
		public double getUnitCost() {
			return this.cost;
		}
		
		public double getUnitWeight() {
			return this.weight;
		}
	}
}
