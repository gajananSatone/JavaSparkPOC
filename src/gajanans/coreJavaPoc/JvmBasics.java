package gajanans.coreJavaPoc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JvmBasics {

	@Test
	public void mutateBookRecordState() throws NoSuchFieldException,
	IllegalAccessException {
		final BookRecord record = new BookRecord("Suzanne Collins", "The Hunger Games");
//		final Field author = record.getClass().getDeclaredField("author");
//		author.setAccessible(true);
//		author.set(record, "Catching Fire");
		assertEquals("Catching Fire", record.getAuthor());
	}


	@Test
	public void primitiveNullPointer() {
		final Integer intObject = 42;
		Integer a = null;

		assert(intObject == 42);
		try {
			final int newIntValue = methodWhichMayReturnNull(intObject);
			fail("Assignment of null to primitive should throw NPE");
		} catch (NullPointerException e) {
			// do nothing, test passed

		}
	}
	private Integer methodWhichMayReturnNull(Integer intValue) {
		return null;
	}

	@Test
	public void stringCreation() {
		String helloString1 = new String("Hello World!");
		String helloString2 = "Hello World!";
		assertEquals(helloString1, helloString2);
	}

	@Test
	public void stringChanges() {
		final String greeting = "Good Morning, Dave";
		final String substring = greeting.substring(4);
		assertTrue(substring.equals("Good"));
		assertFalse(greeting.equals(substring));
		assertTrue(greeting.equals("Good Morning, Dave"));
	}

	private List authors;
	private class Author {
		private final String name;
		private Author(final String name) { this.name = name; }
		public String getName() { return name; }
//		public getDeclaredField(){}
	}

	@Before
	public void createAuthors() {
		authors = new ArrayList();
		authors.add(new Author("Stephen Hawking"));
		authors.add(new Author("Edgar Allan Poe"));
		authors.add(new Author("William Shakespeare"));
	}
	@Test
	public void authorListAccess() {
		final Author author = (Author) authors.get(2);
		assertEquals("William Shakespeare", author.getName());
	}
	private class Field {
		
	}
	
	private class BookRecord {
		
		public BookRecord(String value1 , String Value2){
			
		}
		
		public String getVal1() {
			return Val1;
		}
		public void setVal1(String val1) {
			Val1 = val1;
		}
		public String getAuthor() {
			return Val2;
		}
		public void setVal2(String val2) {
			Val2 = val2;
		}
		String Val1;
		String Val2;
		
	}
}
