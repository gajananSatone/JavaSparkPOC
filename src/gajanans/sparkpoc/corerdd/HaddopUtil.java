//package gajanans.sparkpoc.corerdd;
//
//import org.apache.hadoop.conf.Configuration;
//
//public class HaddopUtil{
//
//	private  Configuration conf;
//
//	public HaddopUtil(String hdfsPath){
//		conf = new Configuration();
//	}
//
//	public void runOpration(String opration , String local_path , String hdfs_path){
//		conf.set("fs.default.name", hdfs_path);
//		FileSystemOperations client = new FileSystemOperations();
//		String hdfsPath = "hdfs://" + args[0] + ":" + args[1];
//
//		if (opration.equals("add")) {
//
//			client.addFile(local_path, hdfs_path, conf);
//
//		} else if (opration.equals("read")) {
//			if (args.length < 2) {
//				System.out.println("Usage: hdfsclient read <hdfs_path>");
//				System.exit(1);
//			}
//
//			client.readFile(local_path, conf);
//
//		} else if (opration.equals("delete")) {
//			if (args.length < 2) {
//				System.out.println("Usage: hdfsclient delete <hdfs_path>");
//				System.exit(1);
//			}
//
//			client.deleteFile(local_path, conf);
//
//		} else if (opration.equals("mkdir")) {
//			if (args.length < 2) {
//				System.out.println("Usage: hdfsclient mkdir <hdfs_path>");
//				System.exit(1);
//			}
//
//			client.mkdir(local_path, conf);
//
//		} else {
//			System.out.println("Usage: hdfsclient add/read/delete/mkdir"
//					+ " [<local_path> <hdfs_path>]");
//			System.exit(1);
//		}
//
//		System.out.println("Done!");
//	}
//}
//
//}
