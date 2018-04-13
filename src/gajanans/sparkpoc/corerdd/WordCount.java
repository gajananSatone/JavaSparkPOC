package gajanans.sparkpoc.corerdd;

import gajanans.spark.env.DataSources;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

// spark-submit --class gajanans.spark.core.RDDBasic --master local  /home/khushi/SkillBrushUp2018/JavaSparkPOC/target/JavaSparkPOC-1.0.jar

public class WordCount {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "serial" })
	public static void main(String[] args) throws Exception {
		
		String resultDir;
//		if (args.length > 0 ){
//			resultDir = args[0];
//		} else {
//			System.err.println("Result directory name is empty or exsiting " + args[0] + " can not be empty.");
//	        System.exit(1);
//		}

		SparkJavaUtil myUtil = new SparkJavaUtil();
		// Create a Java Spark Context.
		JavaSparkContext sc = myUtil.createSparkContext("Word Count");
		// Result Path 
		String resultPath = DataSources.results + myUtil.getDirTimeSfix();
		System.out.println("Result path " + resultPath);
		
		// Load input data
		JavaRDD<String> input = sc.textFile(DataSources.spamData , 2);

		// Split up into words.

		JavaRDD<String> words = input.flatMap(
				new FlatMapFunction<String, String>() {
					public Iterator<String> call(String s) {
						return Arrays.asList(s.split(" ")).iterator();
					}});

		// Transform into pairs and count.
		JavaPairRDD<String , Integer> counts = words.mapToPair(
				new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call (String x){
						return new Tuple2<String, Integer>(x , 1);
					}}).reduceByKey(new Function2<Integer, Integer, Integer>() {
						public Integer call(Integer x , Integer y){return x + y;}});		
		counts.saveAsTextFile(resultPath);		
	}
}


