package gajanans.coreJavaPoc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;
import static org.junit.Assert.*;

public class Pair implements Serializable {
	private final int number;
	private final String name;

	public Pair(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	@Test
	public void writeData() throws IOException {
		final FileOutputStream fos = new FileOutputStream("/tmp/file");
		final ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeInt(101);
		oos.writeBoolean(false);
		oos.writeUTF("Writing a string");
		final Pair pair = new Pair(42, "Forty two");
		oos.writeObject(pair);
		oos.flush();
		oos.close();
		fos.close();
	}

	@Test
	public void readData() throws IOException, ClassNotFoundException {
		final FileInputStream fis = new FileInputStream("/tmp/file");
		final ObjectInputStream ois = new ObjectInputStream(fis);
		final int number = ois.readInt();
		final boolean bool = ois.readBoolean();
		final String string = ois.readUTF();
		final Pair pair = (Pair) ois.readObject();
		assertEquals(101, number);
		assertFalse(bool);
		assertEquals("Writing a string", string);
		assertEquals(42, pair.getNumber());
		assertEquals("Forty two", pair.getName());
	}


}
