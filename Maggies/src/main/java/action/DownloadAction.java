package action;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.struts2.ServletActionContext;
import tool.GlobalDef;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


public class DownloadAction {
	private String basePath = ServletActionContext.getServletContext().getRealPath("/uploads/");
	private String fileName;
	
	
	public String execute(){
		return "success";
	}
	
	public InputStream getInputStream() throws Exception {

		if (GlobalDef.useHdfs) {
			Configuration conf = new Configuration();
			conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
			conf.set("fs.defaultFS", "hdfs://10.50.6.29:9000");
			FileSystem hdfs = FileSystem.get(conf);
			
			Path src = new Path("/" + fileName);
			Path dst = new Path(basePath);
			
			System.out.println("hdfs cpToLocal src:" + src.getName());
			System.out.println("hdfs cpToLocal dst:" + basePath);
			hdfs.copyToLocalFile(false, src, dst, true);
		}
		
		FileInputStream fi = new FileInputStream(new File(basePath, fileName));
		return fi;
	}
	
	public String getFileName() throws UnsupportedEncodingException {
		return new String(fileName.getBytes("GBK"), "ISO-8859-1");
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}