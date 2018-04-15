package gajanans.dataParsing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteJson implements FlatMapFunction <Iterator <Person>, String> , Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Iterator<String> call(Iterator<Person> people) throws Exception {
		ArrayList<String> text = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		while (people.hasNext()) {
			Person person = people.next();
			text.add(mapper.writeValueAsString(person));
		}
		return text.iterator();
	}

}
