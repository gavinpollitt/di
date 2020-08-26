package uk.gav.sp.behaviour.s;

import java.util.List;

public class Strategy {

	public static interface ShippingCoster {
		public double calculate(final List<Shape> shapes);
	}

	public static class Shape {
		private final double size;
		private final double weight;
		
		public Shape(final double size, final double weight) {
			this.size = size;
			this.weight = weight;
		}

		public double getSize() {
			return this.size;
		}
		
		public String toString() {
			return "A shape of size: " + this.size + " weighing " + this.weight;
		}

		public double getWeight() {
			return weight;
		}
	}

	public static class LocalCoster implements ShippingCoster {
		private static double PER_VOLUME = 1.0;
		private static double PER_WEIGHT = 2.0;

		@Override
		public double calculate(final List<Shape> shapes) {
			return shapes.stream().map(s -> s.getSize() * PER_VOLUME + s.getWeight() * PER_WEIGHT).reduce(0d, (a,v) -> a + v);
		}		
	}
	
	public static class NationalCoster implements ShippingCoster {
		private static double PER_VOLUME = 4.0;
		private static double PER_WEIGHT = 6.0;
		private static double FREIGHT_MARGIN = 0.5;

		@Override
		public double calculate(final List<Shape> shapes) {
			return shapes.stream().map(s -> s.getSize() * PER_VOLUME + s.getWeight() * PER_WEIGHT).map(r -> r + r*FREIGHT_MARGIN).reduce(0d, (a,v) -> a + v);
		}		
	}

	public static class OverseasCoster extends NationalCoster {
		private static double EXPORT_DUTY = 1.0;
		private static double AIR_MARGIN = 0.1;

		@Override
		public double calculate(final List<Shape> shapes) {
			double nc = super.calculate(shapes);
			return nc + nc*EXPORT_DUTY + nc*AIR_MARGIN;
		}		
	}
}
