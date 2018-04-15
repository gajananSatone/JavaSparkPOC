package gajanans.coreJavaPoc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

import org.junit.Test;

public class SharedState {
	@Test
	public void sharedState() {
		final ExecutorService executorService = 	Executors.newCachedThreadPool();
		final SimpleCounter c = new SimpleCounter();
		executorService.execute(new CounterSetter(c));
		c.setNumber(200);
		assertEquals(200, c.getNumber());
	}
	
	private static class CounterSetter implements Runnable {
		private final SimpleCounter counter;
		private CounterSetter(SimpleCounter counter) {
			this.counter = counter;
		}
		
		@Override
		public void run() {
			while(true) {
				counter.setNumber(100);
			}
		}
	}
}
