package gajanans.spark.env;

public class DataSources {
	
	public static String linkageFile = "hdfs://localhost:54310//gajanans/linkage";
	public static String userArtistData = "hdfs://localhost:54310/gajanans/last_fm/user_artist_data.txt";
	public static String artistData = "hdfs://localhost:54310/gajanans/last_fm/artist_data.txt";
	public static String artistAliasData = "hdfs://localhost:54310/gajanans/last_fm/artist_alias.txt";
	public static String apacheAccessLog = "hdfs://localhost:54310//gajanans/apacheLogs/access_log";
	public static String apacheErrorLog = "hdfs://localhost:54310//gajanans/apacheLogs/error_log";
	public static String spamData = "hdfs://localhost:54310//gajanans/spam.data";
	
	public static String results = "hdfs://localhost:54310//gajanans/results/";
	
}
