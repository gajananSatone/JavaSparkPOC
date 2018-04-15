package gajanans.sparkpoc.corerdd;


import gajanans.spark.env.DataSources;

import java.util.Arrays;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


public class RDDBasic {
	
	/**
	 * 
	 */
	
	public static void main(String[] args) {
		SparkJavaUtil sparkUtil = new SparkJavaUtil();

		JavaSparkContext sc = sparkUtil.createSparkContext("RDD Basic POC");

		JavaRDD<String> lines = sc.parallelize(Arrays.asList("pandas", "i like pandas"));
		// Print RDD
		sparkUtil.printStringRDD(lines);

		//Use HDFS source
		JavaRDD<String> userArtist = sc.textFile(DataSources.userArtistData);
		//print RDD
		sparkUtil.printStringRDD(userArtist);

	}

	
}
