package gajanans.rddETL;

import org.apache.spark.api.java.function.Function;

public class Contains implements Function<String, Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String query;
	
	public Contains(String query) {this.query = query;}
	
	@Override
	public Boolean call(String v1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
