package gajanans.sparkpoc.corerdd;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.SparkConf;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SparkJavaUtil implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sparkMaster;
	private String propertiesFileName;

	public SparkJavaUtil (){
		this.propertiesFileName = "SparkJavaClusterDefault.properties";
		this.setSparkEnv();
	}

	public SparkJavaUtil (String propertiesFileName){
		this.propertiesFileName = propertiesFileName;
		this.setSparkEnv();
	}

	public Double toDouble(String s){
		if (s.equals("?")){

			return Double.NaN;
		} else
		{
			return Double.parseDouble(s);
		}
	}

	public JavaSparkContext createSparkContext (String appName){

		SparkConf conf = new SparkConf()
		.setAppName(appName)
		.setMaster(this.sparkMaster);

		System.out.println("Env set to use SPARK MASTER as -  " + this.sparkMaster);

		JavaSparkContext sc = new JavaSparkContext(conf);
		return sc;
	}

	public Double naz (Double d) {
		if (d.isNaN()){
			return 0.0;
		}else {
			return d;
		} 
	}

	public String getSparkMaster() {
		return sparkMaster;
	}

	private void setSparkEnv(){
		setSparkMaster(this.propertiesFileName);
	}

	private void setSparkMaster(String propFileName) {

		@SuppressWarnings("unused")
		Properties prop = new Properties();

		try {
			File file = new File(propFileName);
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			this.sparkMaster = properties.getProperty("MASTER");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ImagingOpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 
	public String getDirTimeSfix(){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String dateSfix = sdf.format(timestamp);
		return dateSfix;
	}
	
	public void printStringRDD(JavaRDD<String> userRDD){
		userRDD.foreach(new VoidFunction<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void call(String t) throws Exception {
				System.out.println(t);
			}
		}); 
	}
}
