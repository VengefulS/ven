/**
 * cms-manager 
 * 
 */
package cn.org.cflac.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;


/**
 * @功能描述 
 *  
 * 
 * @author dell
 * @创建时间 2016年1月29日 下午3:31:40
 * @History 修订历史<br>
 * 
 */
public class DownloadHelp {
	private static final Logger log = Logger.getLogger(DownloadHelp.class);
	public static void download(HttpServletRequest req,HttpServletResponse resp,String fileName,File downloadFile){
		long fileLength = downloadFile.length();// 记录文件大小
		long pastLength = 0;// 记录已下载大小
		int rangeSwitch = 0; // 下载状态标记位，0-从头下载；1-从某字节开始下载（bytes=27000-）;2-从某字节到某某字节结束的下载（bytes=27000-39000）
		long toLength = 0;// 记录客户端需要下载字节的末端偏移量（比如bytes=27000-39000，则这个值是为39000）
		long contentLength = 0;// 客户端请求字节量
		String rangeBytes = "";// 用于记录客户端传来的形如”bytes=27000-“或者“bytes=27000-39000”的内容
		RandomAccessFile raf = null;
		OutputStream os = null;
		BufferedOutputStream out = null;// 缓冲s
		byte bytes[] = new byte[1024];// 暂存容器
		if (!StringUtils.isEmpty(req.getHeader("Range"))) {
			// 若客户端传来Range头，说明客户端之前下载了一部分，设置206状态码
			resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
			log.info("客户端支持断点续传Range:" + req.getHeader("Range"));
			rangeBytes = req.getHeader("Range").replaceAll("bytes=", "");
			if (rangeBytes.indexOf("-") == rangeBytes.length() - 1) { // rangeSwitch状态为1.（bytes=27000-）
				rangeSwitch = 1;
				pastLength = Long.parseLong(rangeBytes.substring(0, rangeBytes.indexOf('-')).trim());
				contentLength = fileLength - pastLength + 1;
			} else {// rangeSwitch状态为2.（bytes=27000-39000）
				rangeSwitch = 2;
				String tmpStart = rangeBytes.substring(0, rangeBytes.indexOf('-')).trim();
				String tmpEnd = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length()).trim();
				pastLength = Long.parseLong(tmpStart);
				toLength = Long.parseLong(tmpEnd);
				contentLength = toLength - pastLength + 1;
			}
		} else { // 否则从头开始下载，客户端不支持断点续载
			contentLength = fileLength; // 200,无需显式设置
		}
		//resp.reset();
		resp.setHeader("Accept-Ranges", "bytes");
		if (pastLength != 0) { // 不是从头开始下载
			log.info("文件断点下载");
			switch (rangeSwitch) {
			case 1: {
				// 响应的格式是: Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
				String contentRange = new StringBuilder("bytes ").append(new Long(pastLength).toString()).append("-")
						.append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString())
						.toString();
				resp.setHeader("Content-Range", contentRange);
				break;
			}
			case 2: {
				String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
				resp.setHeader("Content-Range", contentRange);
				break;
			}
			default: {
				break;
			}
			}
		} else {
			log.info("文件从头下载");
		}
		MappedByteBuffer mapBuf=null;
		try {
			resp.addHeader("Content-Disposition",
					"attachment; filename=\"" + new String(fileName.getBytes(), "ISO-8859-1") + "\"");
			resp.setContentType("application/octet-stream");
			resp.setContentLength((int)contentLength);;
			os = resp.getOutputStream();
			out = new BufferedOutputStream(os);
			raf = new RandomAccessFile(downloadFile, "r");
			mapBuf=raf.getChannel().map(FileChannel.MapMode.READ_ONLY, pastLength,downloadFile.length()-pastLength);
			while(mapBuf.hasRemaining()){
				out.write(mapBuf.get());
			}
		} catch (Exception e) {
			log.error("文件下载出现异常{}",e);
		} finally {
			try {
	            if (out != null) {
	                out.close();
	            }
	        } catch (IOException ioe) {
	            // ignore
	        }if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			unmap(mapBuf);
		}
	}	
	
	/**
	 * 内存释放
	 * @param byteBuffer
	 */
	@SuppressWarnings("unchecked")
	private static void unmap(final MappedByteBuffer byteBuffer){
		if(byteBuffer==null) return;
		AccessController.doPrivileged(new PrivilegedAction() {  
			  public Object run() {  
			    try {  
			      Method getCleanerMethod = byteBuffer.getClass().getMethod("cleaner", new Class[0]);  
			      getCleanerMethod.setAccessible(true);  
			      sun.misc.Cleaner cleaner = (sun.misc.Cleaner)   
			      getCleanerMethod.invoke(byteBuffer, new Object[0]);  
			      cleaner.clean();  
			      log.info("MappedByteBuffer释放完毕");
			    } catch (Exception e) {  
			      e.printStackTrace();
			      log.warn("内存释放异常{}",e);
			    }  
			    return null;  
			  }  
			});
	}
}
