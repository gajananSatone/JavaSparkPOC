package gajanans.rddETL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
//import java.util.List;

import gajanans.spark.env.DataSources;
import gajanans.sparkpoc.corerdd.SparkJavaUtil;

import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class Aggregations {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		SparkJavaUtil myUtil = new SparkJavaUtil();
		JavaSparkContext sc = myUtil.createSparkContext("Aggregations Transformation POC");

		@SuppressWarnings("unused")
		JavaPairRDD<Integer , Integer> nums = sc.textFile(DataSources.numsLine)
		.flatMap(new FlatMapFunction<String, Integer>() {
			@Override
			public Iterator<Integer> call(String line) {
				ArrayList<Integer> intArray = new ArrayList<Integer>();
				for (String x : Arrays.asList(line.split(","))){
					try {
						intArray.add(Integer.parseInt(x));						
					} catch (NumberFormatException e)  {
						intArray.add(null);
					}
				}
				return intArray.iterator();
			}
		})
		.mapToPair(new PairFunction<Integer, Integer, Integer>() {
			@Override
			public Tuple2<Integer, Integer> call(Integer t)
					throws Exception {
				return new Tuple2<Integer, Integer>(t, 1);
			}});

		for (Tuple2<Integer, Integer> i : nums.collect()){
			System.out.println("Key " + i._1 + " Value " +  i._2);
		}


		Function<Integer, AvgCount> createAcc = new Function<Integer, AvgCount>() {
			@Override
			public AvgCount call(Integer x) throws Exception {
				// TODO Auto-generated method stub
				return new AvgCount(x, 1);
			}			
		};

		Function2<AvgCount, Integer, AvgCount> addAndCount = new Function2<AvgCount, Integer, AvgCount>() {

			@Override
			public AvgCount call(AvgCount a, Integer x) throws Exception {
				a.total_ += x;
				a.num_ += 1;
				return a;
			}
		};

		Function2<AvgCount, AvgCount, AvgCount> combine = new Function2<AvgCount, AvgCount, AvgCount>() {

			@Override
			public AvgCount call(AvgCount a, AvgCount b) throws Exception {
				a.total_ += b.total_;
				a.num_ += b.num_ ;
				return a;
			}

		};

		AvgCount initial = new AvgCount(0,0);
//		JavaPairRDD<String, AvgCount> avgCounts = nums.combineByKey(createAcc, addAndCount, combine); 

	}

}
