package uk.gav.sp.struct.p;

import uk.gav.sp.ClientApplication;
import uk.gav.sp.struct.p.Proxy.Circle;
import uk.gav.sp.struct.p.Proxy.PrintJob;
import uk.gav.sp.struct.p.Proxy.Rectangle;
import uk.gav.sp.struct.p.Proxy.ShapePrinter;


/**
 * 
 * @author regen 
 * Utilising a proxy ShapePrinter class in lieu of a heavyweight ShapePrinterServer class. The proxy will 
 * have a handle on the server class and deal with security etc, but this will be invisible to client. Only when the client is
 * ready to print is the work finally delegated to the server.
 */
public class PClient extends ClientApplication {

	public static void main(String[] args) throws Exception {
		ClientApplication.run(PClient.class);
	}

	/*
	 * Perform the client to add shapes to queue and then flush to server.
	 */
	@Override
	public void clientCode() {
		String user = "Rita";

		ShapePrinter sp = ShapePrinter.getInstance();
		System.out.println("ShapePrinter in use--> " + sp);

		System.out.println(user+"'s jobs");
		sp.addToQueue(new PrintJob(new Rectangle(5, 5), user));
		sp.addToQueue(new PrintJob(new Rectangle(2, 1), user));
		sp.addToQueue(new PrintJob(new Rectangle(4, 12), user));

		user = "Sue";
		System.out.println(user+"'s jobs");
		sp.addToQueue(new PrintJob(new Circle(7), user));
		sp.addToQueue(new PrintJob(new Circle(4), user));

		sp.print();

		sp = ShapePrinter.getInstance();
		System.out.println("ShapePrinter in use--> " + sp);
		
		user = "Bob";
		System.out.println(user+"'s jobs");
		sp.addToQueue(new PrintJob(new Rectangle(10, 10), user));
		sp.addToQueue(new PrintJob(new Rectangle(12, 1), user));
		
		sp.print();
		
		System.out.println(user+"'s jobs");
		sp.addToQueue(new PrintJob(new Circle(4), user));

	}
}
