package gajanans.dataParsing;

import gajanans.spark.env.DataSources;
import gajanans.sparkpoc.corerdd.SparkJavaUtil;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class LoadAndSaveJson {

	public static void main(String[] args) {
		SparkJavaUtil mySparkUtil = new SparkJavaUtil();
		JavaSparkContext sc = mySparkUtil.createSparkContext("Loading and saving Json");
		
		JavaRDD<String> input = sc.textFile(DataSources.NameInfoJson);
		JavaRDD<Person> result = input.mapPartitions(new ParseJson());

		
//		for (Person line : result.collect()){
//			System.out.println(line.name + " - " + line.lovesPandas);
//		}

	}

}
