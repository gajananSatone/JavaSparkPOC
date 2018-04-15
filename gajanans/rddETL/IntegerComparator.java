package gajanans.rddETL;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer a, Integer b) {
		return String.valueOf(a).compareTo(String.valueOf(b));		
	}

}
