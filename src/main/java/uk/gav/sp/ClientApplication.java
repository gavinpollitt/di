package uk.gav.sp;

public abstract class ClientApplication {
	public abstract void clientCode() throws Exception;
	
	protected static <T extends ClientApplication> void run(final Class<T> clazz) throws Exception {
		ClientApplication app = clazz.getDeclaredConstructor().newInstance();
		app.clientCode();
	}
}
