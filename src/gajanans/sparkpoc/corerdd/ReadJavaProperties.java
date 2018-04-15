//package gajanans.sparkpoc.corerdd;
//import java.awt.image.ImagingOpException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Enumeration;
//
//import java.util.Properties;
//
//public class ReadJavaProperties {
//
//	public static void main(String[] args) {
//		Properties prop = new Properties();
// 
//		try {
//			File file = new File("SparkJavaCluster.properties");
//			FileInputStream fileInput = new FileInputStream(file);
//			Properties properties = new Properties();
//			properties.load(fileInput);
//			fileInput.close();
//
//			Enumeration enuKeys = properties.keys();
//			while (enuKeys.hasMoreElements()) {
//				String key = (String) enuKeys.nextElement();
//				String value = properties.getProperty(key);
//				System.out.println(key + ": " + value);
//			}
//			
//			System.out.println(properties.getProperty("MASTER", "local"));
//			System.out.println(properties.getProperty("MASTER"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (ImagingOpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//
//	}
//
//}
