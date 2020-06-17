package uk.gav.sp.struct.p;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proxy {


	public static final class Environment {
		private static Map<String, String> ROLES = new HashMap<>();

		static {
			ROLES.put("Rita", "Rectangle");
			ROLES.put("Bob", "Rectangle");
			ROLES.put("Sue", "Circle");
		}

		public static boolean hasRole(final String user, final Shape shape) {
			String userRole = ROLES.get(user);
			return userRole == null ? false : shape.getDescription().toLowerCase().contains(userRole.toLowerCase());
		}
	}

	public static interface ShapePrinter {
		public void addToQueue(final PrintJob pj);

		public void print();
		
		public static ShapePrinter getInstance() {
			return ShapePrinterServer.getInstance();
		}
	}

	public static final class PrintJob {
		private final Shape s;
		private final String u;

		public PrintJob(final Shape s, final String u) {
			this.s = s;
			this.u = u;
		}

		public Shape getShape() {
			return this.s;
		}

		public String getUser() {
			return this.u;
		}

	}

	private static final class ShapePrinterServer implements ShapePrinter {
		private static ShapePrinterServer _instance = new ShapePrinterServer();

		private List<PrintJob> serverQueue = new ArrayList<>();
		private boolean init = false;

		private final static ShapePrinter getInstance() {
			return new ShapePrinterProxy(_instance);
		}

		private ShapePrinterServer() {}
		
		@Override
		public void addToQueue(PrintJob pj) {
			System.out.println("ShapePrinter to addToQueue--> " + this);
			this.serverQueue.add(pj);
		}

		@Override
		public void print() {
			System.out.println("ShapePrinter to print--> " + this);
			this.init();
			serverQueue.stream().forEach(this::printEntry);
			serverQueue.clear();
		}

		private void printEntry(final PrintJob pj) {
			System.out.print("Print Job -->");
			System.out.println("User: " + pj.getUser() + ", Shape: " + pj.getShape().getDescription());
		}

		private void init() {
			if (!this.init) {
				this.init = true;
				System.out.println("SHAPEPRINTERSERVER:: Switching on from Standby");
				System.out.println("SHAPEPRINTERSERVER:: Checking inks");
				System.out.println("SHAPEPRINTERSERVER:: Initialising print memory");
				System.out.println("SHAPEPRINTERSERVER:: Validating print queue");
			}
		}
	}

	public static final class ShapePrinterProxy implements ShapePrinter {
		private static int proxyId = -1;

		private final ShapePrinter truePrinter;

		private List<PrintJob> localQueue = new ArrayList<>();

		private ShapePrinterProxy(final ShapePrinterServer server) {
			this.truePrinter = server;
			proxyId++;
		}

		@Override
		public void addToQueue(final PrintJob pj) {
			System.out.println("ShapePrinter to addToQueue--> " + this);
			if (Environment.hasRole(pj.getUser(), pj.getShape())) {
				this.localQueue.add(pj);
			} else {
				throw new SecurityException(
						pj.getUser() + " does not have permission to add " + pj.getShape().getDescription());
			}
		}

		@Override
		public void print() {
			System.out.println("ShapePrinter to print--> " + this);
			localQueue.stream().forEach(truePrinter::addToQueue);
			localQueue.clear();
			this.truePrinter.print();
		}

		@Override
		public String toString() {
			return this.getClass().getSimpleName() + "-->" + ShapePrinterProxy.proxyId;
		}
	}

	public static interface Shape {
		String getDescription();
	}

	public static class Rectangle implements Shape {
		private final int l, b;

		public Rectangle(final int l, final int b) {
			this.l = l;
			this.b = b;
		}

		public int getLength() {
			return this.l;
		}

		public int getBreadth() {
			return this.b;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Rectangle (" + this.l + "," + this.b + ")";
		}
	}

	public static class Circle implements Shape {
		private final int r;

		public Circle(final int r) {
			this.r = r;
		}

		public int getRadius() {
			return this.r;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Circle (" + this.r + ")";
		}
	}
}
