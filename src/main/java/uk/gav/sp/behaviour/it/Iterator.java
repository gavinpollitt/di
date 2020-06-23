package uk.gav.sp.behaviour.it;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Iterator {
	public static interface Shape {
		public String getDescription();
	}

	public static abstract class ThreeDShape implements Shape {
		public String getDescription() {
			return "3D->" + this.getClass().getSimpleName();
		}
	}

	public static abstract class TwoDShape implements Shape {
		public String getDescription() {
			return "2D->" + this.getClass().getSimpleName();
		}
	}

	public static class Sphere extends ThreeDShape {
	}

	public static class Cube extends ThreeDShape {
	}

	public static class Circle extends TwoDShape {
	}

	public static class Square extends TwoDShape {
	}

	public static class ShapeBag{

		private List<Shape> shapes = new ArrayList<>();

		public void add(final Shape shape) {
			shapes.add(shape);
		}

		public ShapeIterator iterator() {
			return this.iterator(null);
		}

		public ShapeIterator iterator(Class<? extends Shape> clazz) {
			return new ShapeIteratorImpl(clazz);
		}

		public interface ShapeIterator {
			public boolean hasNext();

			public Shape next();
		}

		private class ShapeIteratorImpl implements ShapeIterator{
			private int index = -1;
			private Class<? extends Shape> type;
			//private boolean active = true;
			private BooleanSupplier active = () -> (this.index < ShapeBag.this.shapes.size());
			private boolean stale = false;

			public ShapeIteratorImpl(Class<? extends Shape> type) {
				this.type = type;
			}

			@Override
			public boolean hasNext() {
				boolean nx = !this.active.getAsBoolean()?false:nextOfType();
				
				return nx;
			}

			@Override
			public Shape next() {
				if (!this.active.getAsBoolean() || (this.stale && !this.nextOfType())) {
					throw new IllegalArgumentException("Underlying exception is exhausted");
				}
					
				Shape nx = ShapeBag.this.shapes.get(this.index);
				this.stale = true;
				return nx;
			}

			private boolean nextOfType() {
				
				this.index += 1;
				if (!this.active.getAsBoolean()) return false;

				this.stale = false;

				boolean done = false;
				Shape shape = ShapeBag.this.shapes.get(this.index);
				while (!done) {

					if (this.index < ShapeBag.this.shapes.size()) {
						if (!tCheck(shape)) {
							index +=1;
							if (this.index == ShapeBag.this.shapes.size()) {
								done = true;
							}
							else {
								shape = ShapeBag.this.shapes.get(this.index);
							}
						}
						else {
							done = true;
						}
					} else {
						done = true;
					}
				}
				
				return this.active.getAsBoolean();
			}
			
			private boolean tCheck(final Shape sh) {
				boolean match = true;
				if (this.type!=null) {
					//System.out.println(TwoDShape.class.isAssignableFrom(Square.class));
					match = this.type.isAssignableFrom(sh.getClass());
				}
				
				return match;
			}
		}
	}
}
