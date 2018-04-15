package gajanans.dataParsing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.codehaus.jackson.map.ObjectMapper;

public class ParseJson implements FlatMapFunction<Iterator<String>, Person> , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Iterator<Person> call(Iterator<String> lines) throws Exception {
		ArrayList<Person> people = new ArrayList<Person>();
		ObjectMapper mapper = new ObjectMapper();

		while (lines.hasNext()){
			String line = lines.next();
			try{
				people.add(mapper.readValue(line, Person.class));
			} catch (Exception e){
				// Skip records on failure 
			}
		}

		return people.iterator();
	}

	//	public static void main(String[] args) {
	//		// TODO Auto-generated method stub
	//
	//	}

}
