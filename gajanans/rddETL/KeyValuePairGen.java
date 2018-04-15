package gajanans.rddETL;

import gajanans.spark.env.DataSources;
import gajanans.sparkpoc.corerdd.SparkJavaUtil;

import org.apache.spark.HashPartitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class KeyValuePairGen {

	public static void main(String[] args) {

		SparkJavaUtil myUtil = new SparkJavaUtil();
		JavaSparkContext sc = myUtil.createSparkContext("Key value pair genrator");

		JavaRDD<String> emp = sc.textFile(DataSources.EmployeesNW);

		//		for (String line : emp.collect()){
		//			System.out.println(line);
		//		}

		PairFunction<String, Integer, String> keyData = new PairFunction<String, Integer, String>() {
			@Override
			public Tuple2<Integer, String> call(String x) throws Exception {
				return new Tuple2<Integer, String>(Integer.parseInt(x.split(",")[0]) , x);				
			}
		};

		JavaPairRDD<Integer, String> pairEmp =  emp.mapToPair(keyData).partitionBy(new HashPartitioner(8));
		String targetDir = "MappedKey_"+ myUtil.getDirTimeSfix();
//		pairEmp.saveAsTextFile(DataSources.results + targetDir);	
		//		for (Tuple2<Integer, String> line : pairEmp.collect()){
		//			System.out.println("Key : " + line._1 + " Value : " + line._2);			
		//		}
		
		
	}

}
