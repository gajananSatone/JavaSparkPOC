package gajanans.rddETL;

import java.util.Arrays;
import java.util.Iterator;

import gajanans.spark.env.DataSources;
import gajanans.sparkpoc.corerdd.SparkJavaUtil;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class KeyValueTransformation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkJavaUtil myUtil = new SparkJavaUtil();
		JavaSparkContext sc = myUtil.createSparkContext("RDD Transformation POC");

		JavaRDD<String> lines = sc.textFile(DataSources.longText);

		PairFunction<String, String, String>	keyData = new PairFunction<String, String, String>() {
			public Tuple2<String, String> call (String x){
				return new Tuple2(x.split("'")[0],x);
			}
		};

		Function<Tuple2<String, String>, Boolean> longWordFilter = 
				new Function<Tuple2<String,String>, Boolean>() {
			public Boolean call (Tuple2<String, String> keyValue){
				return (keyValue._2().length()<20);
			}
		};

		JavaPairRDD<String, String> pairs = lines.mapToPair(keyData);
		JavaPairRDD<String, String> result = pairs.filter(longWordFilter);

		//Word count key value pair
		JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
			public Iterator<String> call(String x) {
				return Arrays.asList(x.split(" ")).iterator();							
			}
		});

		JavaPairRDD<String, Integer> countResult = words.mapToPair(
				new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call (String x) {return new Tuple2(x, 1);}
				}).reduceByKey(
						new Function2<Integer, Integer, Integer>() {
							@Override

							public Integer call(Integer a, Integer b) throws Exception {
								// TODO Auto-generated method stub
								return a + b;
							}
						});

	}


}
