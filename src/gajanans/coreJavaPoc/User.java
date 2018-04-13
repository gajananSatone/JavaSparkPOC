package gajanans.coreJavaPoc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.LinkedHashMap;

import org.junit.Test;

import static org.junit.Assert.*;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
//	private HashMap <String, String> MyHashMap; 
	
	private transient String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	@Test
	public void transientField()
	throws IOException, ClassNotFoundException {
		final User user = new User("Noel", "secret321");
		final FileOutputStream fos = new FileOutputStream("/tmp/user");
		final ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(user);
		oos.flush();
		oos.close();
		fos.close();
		final FileInputStream fis = new FileInputStream("/tmp/user");
		final ObjectInputStream ois = new ObjectInputStream(fis);
		final User deserialized = (User) ois.readObject();
		assertEquals("Noel", deserialized.getUsername());
		assertNull(deserialized.getPassword());
		ois.close();
	}
}
