package gajanans.rddETL;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;

import gajanans.spark.env.DataSources;
import gajanans.sparkpoc.corerdd.SparkJavaUtil;

public class TransformationPoc {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkJavaUtil myUtil = new SparkJavaUtil();
		JavaSparkContext sc = myUtil.createSparkContext("RDD Transformation POC");

		JavaRDD<String> inputRDD = sc.textFile(DataSources.lsLog);

		JavaRDD<String> errorRDD = inputRDD.filter(x -> x.contains("error"));

		myUtil.printStringRDD(errorRDD);
		//
		JavaRDD<String> warningRDD = inputRDD.filter(x -> x.contains("warning"));

		//		myUtil.printStringRDD(warningRDD);

		JavaRDD<String> getRequestRDD = inputRDD.filter(x -> x.contains("GET"));
		//		myUtil.printStringRDD(getRequestRDD);
		System.out.println("Input had : " + getRequestRDD.count() + " GET request");

//		for (String line : getRequestRDD.collect()){
//			System.out.println(line);
//		}

		JavaRDD<Integer> myIntRDD = sc.parallelize(Arrays.asList(1, 2, 3, 4));
		JavaRDD<Integer> results = myIntRDD.map(x -> x *x );

		//		System.out.println(StringUtils.join(results.collect(), " , "));

		Integer reducedRDD = myIntRDD.reduce(new Function2<Integer, Integer, Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer call(Integer v1, Integer v2) throws Exception {
				return v1 + v2;
			}
		} );

		// Using Lambda
		Integer count = myIntRDD.reduce((Integer v1, Integer v2) -> v1 + v2);

		//		System.out.println(count);

		Integer foldCount = myIntRDD.fold(3, (Integer v1, Integer v2) -> v1 + v2);
		//		System.out.println(foldCount);

		//aggregate

		Function2<AvgCount, Integer, AvgCount> addAndCount =
				new Function2<AvgCount, Integer, AvgCount>() {

			public AvgCount call(AvgCount a, Integer x) {
				a.total_ += x;
				a.num_ += 1;
				return a;
			}
		};

		Function2<AvgCount, AvgCount, AvgCount> combine =
				new Function2<AvgCount, AvgCount, AvgCount>() {

			public AvgCount call(AvgCount a, AvgCount b) {
				a.total_ += b.total_;
				a.num_ += b.num_;
				return a;
			}

		};

		AvgCount initial = new AvgCount(0, 0);
		AvgCount result = myIntRDD.aggregate(initial, addAndCount, combine);

		//		System.out.println("Aggregated results : " + result.avg());

		JavaDoubleRDD doubleResults = myIntRDD.mapToDouble(x -> ((double) x * x));

		//		System.out.println( 
		//				"Mean "	+ doubleResults.mean()
		//				+ "\n Count " +  doubleResults.count()
		//				+ "\n Approximate Count " + doubleResults.countApproxDistinct(0.2)
		//				+ "\n Max " + doubleResults.max()
		//				+ "\n Stats " + doubleResults.stats()
		//				);

		JavaRDD<String> linkageFileWithoutHeader = sc.textFile(DataSources.linkageFile).filter(x -> ! x.contains("id_1"));

		for (String line : linkageFileWithoutHeader.take(10)){
			//					System.out.println(line);
			String[] pieces = line.split(",");
			//					ArrayList<String> ;
			Integer id1 = Integer.parseInt(pieces[0]);
			Integer id2 = Integer.parseInt(pieces[1]);

			//					Double[] scores = Arrays.copyOfRange(pieces, 2, 11) //pieces.
			Object[] scores = Arrays.copyOfRange(pieces, 2, 11); //pieces.
			
			for (int i = 0 ; i <= scores.length; i++){
				System.out.println(" | "+ scores[i]);
			}
		}

	}

	public void parseLine (String line){
		String[] pieces = line.split(",");
		//		ArrayList<String> ;
		Integer id1 = Integer.parseInt(pieces[0]);
		Integer id2 = Integer.parseInt(pieces[1]);

		Object[] scores = Arrays.copyOfRange(pieces, 2, 11); //pieces.
		for (String item : Arrays.copyOfRange(pieces, 2, 11) ){
			System.out.println(item);
		}
		//pieces.slice(2, 11).map(toDouble)
		//				boolean matched = pieces(11).toBoolean
	}

}
