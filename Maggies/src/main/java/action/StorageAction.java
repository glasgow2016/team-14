package action;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import tool.GlobalDef;
import tool.RedisTool;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StorageAction extends ActionSupport {

	private File upload;
	private String uploadFileName;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String gotoUploadPage() {
		return SUCCESS;
	}

	public String upload() throws Exception {

		String fileName = uploadFileName;
		long fileSize = 0;

		String destPath = ServletActionContext.getServletContext().getRealPath("/uploads");

		File dest = new File(destPath, uploadFileName);

		FileUtils.copyFile(upload, dest);
		fileSize = FileUtils.sizeOf(dest);

		if (GlobalDef.useHdfs) {
			Configuration conf = new Configuration();
			conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
			conf.set("fs.defaultFS", "hdfs://10.50.6.29:9000");
			FileSystem hdfs = FileSystem.get(conf);

			FileInputStream in = new FileInputStream(new File(dest.getAbsolutePath()));
			Path dst = new Path("/" + fileName);
			FSDataOutputStream out = hdfs.create(dst, (short)1);
			IOUtils.copyBytes(in, out, 2048, true);

			dest.delete();
		}
		/**
		 * 将文件信息写入redis
		 */
		FileAttr fa = new FileAttr();
		fa.setName(fileName);
		fa.setSize(String.valueOf(fileSize));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = formatter.format(new Date());
		fa.setDt(dt);

		Map map = ActionContext.getContext().getSession();
		String user = (String) map.get("USERNAME");

		String tabname = GlobalDef.TabUserFilePrefix + user;

		Gson gson = new Gson();
		RedisTool.uploadFile(tabname, fileName, gson.toJson(fa));

		msg = "上传成功";
		System.out.println("uploadWithAttr:" + fileName);
		return "success";
	}

	private List<FileAttr> fileList;

	public List<FileAttr> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileAttr> fileList) {
		this.fileList = fileList;
	}

	public String listfile() throws Exception {
		System.out.println("action listfile");

		Map map = ActionContext.getContext().getSession();
		String user = (String) map.get("USERNAME");

		String tabname = GlobalDef.TabUserFilePrefix + user;
		Map<String, String> files = RedisTool.listFiles(tabname);

		fileList = new ArrayList<FileAttr>();
		Gson gson = new Gson();
		if (files != null) {
			for (String s : files.values()) {
				FileAttr fa = gson.fromJson(s, FileAttr.class);
				fileList.add(fa);
			}
		}

		return "success";
	}

	private String delFilename;

	public String getDelFilename() {
		return delFilename;
	}

	public void setDelFilename(String delFilename) {
		this.delFilename = delFilename;
	}

	public String delfile() throws Exception {
		System.out.println("删除文件的名：" + delFilename);

		if (!GlobalDef.useHdfs) {
			String destPath = ServletActionContext.getServletContext().getRealPath("/uploads");

			File dest = new File(destPath, delFilename); // 服务器的文件
			dest.delete();
		} else {
			Configuration conf = new Configuration();
			conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
			conf.set("fs.defaultFS", "hdfs://10.50.6.29:9000");
			FileSystem hdfs = FileSystem.get(conf);
			Path dst = new Path("/" + delFilename);

			hdfs.delete(dst, true);
		}

		Map map = ActionContext.getContext().getSession();
		String user = (String) map.get("USERNAME");

		String tabname = GlobalDef.TabUserFilePrefix + user;
		RedisTool.delUserFile(tabname, delFilename);

		msg = "删除文件：" + delFilename + " 成功";
		return "success";
	}
}
