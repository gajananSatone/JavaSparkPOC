package gajanans.rddETL;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.spark.Partitioner;;

public class DomainNamePartitioner extends Partitioner {

	/**  
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer numPartition;
	
	public DomainNamePartitioner(Integer numPartition){
		this.numPartition = numPartition;
	}

	@Override
	public int getPartition(Object key) {
		String domain = null;
		try {
			domain = new URL(key.toString()).getHost();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		Integer code = (domain.hashCode() % numPartitions());

		if (code < 0 ){
			return code += numPartition;
		} else {
			return code;
		}		 
	}

	@Override
	public int numPartitions() {
		return numPartition;
	}
	
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((numPartition == null) ? 0 : numPartition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		DomainNamePartitioner other = (DomainNamePartitioner) obj;
	
		if (numPartition == null) {
			if (other.numPartition != null)
				return false;
		} else if (!numPartition.equals(other.numPartition))
			return false;
		return true;
	}
}
