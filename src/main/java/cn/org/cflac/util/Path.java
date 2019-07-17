package cn.org.cflac.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Path {
	//"http://10.1.11.120:9000/resource"
	//"http://10.1.100.152:9000/resource"
	//"http://localhost:9000/resource"
	
	
	public static String PIC_UPLOAD_PATH;
	
	
	@Value("${pic_upload_path}")
	public void setPicUploadPath(String pic_upload_path) {
		PIC_UPLOAD_PATH = pic_upload_path;
	}


	// /opt/lar/files/videomanager/videoresource
	//F:/opt/lar/files/videomanager/videoresource
	public static  String UPLOAD_PATH ; 
	
	@Value("${upload_path}")
	public void setUploadPath(String upload_path) {
		UPLOAD_PATH = upload_path;
	}
	//public static final String FFMPEG_PATH = "/opt/lar/ffmpeg/ffmpeg.sh "; 
	
	public static  String DOWNLOAD_ZIP_PATH ; 
	// /opt/lar/files/videomanager/videoresource/tempzip
	//public static final String DOWNLOAD_ZIP_PATH ="F:/opt/lar/files/videomanager/videoresource/tempzip";
	@Value("${download_zip_path}")
	public void setDownloadZipPath(String download_zip_path) {
		DOWNLOAD_ZIP_PATH = download_zip_path;
	}
	
	
	
	
	public static  String CUT_PATH ="00/resource";
	
	
	//上传时的临时路径
	//F:/opt/lar/files/videomanager
	// /opt/lar/files/videomanager
	public static  String TEMP_PATH;
	@Value("${temp_path}")
	public void setTempPath(String temp_path) {
		TEMP_PATH = temp_path;
	}
	
}
