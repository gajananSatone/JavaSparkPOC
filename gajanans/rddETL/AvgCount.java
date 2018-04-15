package gajanans.rddETL;

import java.io.Serializable;

import org.apache.spark.api.java.function.Function2;

public class AvgCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1282292569810574088L;

	public int total_;
	public int num_;

	public AvgCount(int total , int num){
		this.total_ = total;
		this.num_ = num;
	}

	public float avg(){
		return total_ / num_;
	}


}