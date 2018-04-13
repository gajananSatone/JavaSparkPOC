package gajanans.coreJavaPoc;

public class ValueContainer {
	@SuppressWarnings("unused")
	private final String value;
	public ValueContainer(final String value) {
		this.value = value;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.printf("Finalizing for [%s]%n", toString());
	}
	/* equals, hashCode and toString omitted */
}
