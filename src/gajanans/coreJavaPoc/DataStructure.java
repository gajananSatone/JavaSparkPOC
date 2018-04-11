package gajanans.coreJavaPoc;

import java.util.Hashtable;
import java.util.LinkedHashMap;

public class DataStructure {

	public static void main(String[] args) {
		LinkedHashMap<String, String> myMap = new LinkedHashMap<String, String>();
		Hashtable<String, String> ht = new Hashtable<>();
		
		Integer myIntValue = 111123;
		Long myLongValue  = 23L;
		Double myDoubleValue = 23.45;
		Float myFloatVAlue = (float) 23.45;
		
		
		Double dubResult = myIntValue / myDoubleValue ;
		Long longResult = (long) (myIntValue / myDoubleValue) ;
		Float floatResult = myIntValue / myFloatVAlue ;
		
		System.out.println(dubResult);
		System.out.println(longResult);
		System.out.println(floatResult);
		
		/*
		 * Multiplication by 64
		 */
		System.out.println(dubResult * 64);
		System.out.println(longResult * 64);
		System.out.println(floatResult * 64);
		
	}
	 
}
