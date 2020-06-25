package uk.gav.sp.behaviour.ob;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class Observer {
	
	public static interface ShapeEventPublisher {
		public void notify(final Event event, final ThreeDShape shape);
		public void addSubscriber(final ShapeEventSubscriber sub);
		public void removeSubscriber(final ShapeEventSubscriber sub);
	}
	
	public static interface ShapeEventSubscriber {
		public void receive(final Event event, final ThreeDShape shape);
	}
	
	public static class ThreeDShape {
		private double size;
		
		public ThreeDShape(final double size) {
			this.size = size;
		}

		public double getSize() {
			return size;
		}

	}
	
	public static class TotalShapeSizer implements ShapeEventSubscriber {
		private double total;
		
		private final Map<Event,Consumer<ThreeDShape>> events = new HashMap<>();
		
		public TotalShapeSizer() {
			events.put(Event.ADD, s -> this.total += s.getSize());
			events.put(Event.REMOVE, s -> this.total -= s.getSize());
		}

		@Override
		public void receive(final Event event, final ThreeDShape shape) {
			events.get(event).accept(shape);
		}	
		
		public double getTotal() {
			return this.total;
		}
	}
	
	public static class MaxShape implements ShapeEventSubscriber {
		private ThreeDShape max;

		@Override
		public void receive(final Event event, final ThreeDShape shape) {
			if (event == Event.ADD) {
				if (max == null || (shape.getSize() > this.max.getSize())) {
					this.max = shape;
				}
			}
		}	
		
		public double getMax() {
			return this.max.size;
		}

	}
		
	
	public static class ShapeBag implements ShapeEventPublisher{
		private final Set<ThreeDShape> shapes = new HashSet<>();
		private final Set<ShapeEventSubscriber> eventListeners = new HashSet<>();

		public void addShape(final ThreeDShape shape) {
			this.shapes.add(shape);
			this.notify(Event.ADD, shape);
		}
		
		public void removeShape(final ThreeDShape shape) {
			if (this.shapes.contains(shape)) {
				this.shapes.remove(shape);
				this.notify(Event.REMOVE, shape);
			}
			else {
				throw new IllegalArgumentException("Shape does not exist to remove from Bag");
			}
		}
		
		@Override
		public void notify(final Event event, final ThreeDShape shape) {
			eventListeners.stream().forEach(l -> l.receive(event, shape));
		}
		
		@Override
		public void addSubscriber(final ShapeEventSubscriber sub) {
			this.eventListeners.add(sub);
		}
		@Override
		public void removeSubscriber(final ShapeEventSubscriber sub) {
			this.eventListeners.remove(sub);
		}
		
	}
	
	private static enum Event {
		ADD,
		REMOVE;
	}
	
	protected static enum Colour {
		RED,
		BLUE,
		GREEN;
	}
}
