package cn.org.cflac.home.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;
import cn.org.cflac.home.service.ActivityService;
import cn.org.cflac.home.service.VideoService;
import cn.org.cflac.util.DownloadHelp;
import cn.org.cflac.util.Path;

@RestController
@RequestMapping("/videod")
public class FileDownloadController {
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private ActivityService activityService;
	//普通java文件下载方法，适用于所有框架  
	@RequestMapping("/download")
    public String downloadFiles(HttpServletRequest request,HttpServletResponse res) throws IOException {
         String zipBasePath = Path.DOWNLOAD_ZIP_PATH;
         
         System.out.println(request.getParameter("id"));
         String actId = request.getParameter("id");
         
        /* List list = */
         
        
         //IO流实现下载的功能  
        res.setContentType("text/html; charset=UTF-8"); //设置编码字符  
        res.setContentType("application/octet-stream"); //设置内容类型为下载类型  
        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框   

        //将附件中多个文件进行压缩，批量打包下载文件
        //创建压缩文件需要的空的zip包  
        //String zipBasePath=request.getSession().getServletContext().getRealPath("/upload/zip");  
        // System.out.println(zipBasePath);
        //activityService.findActivityNameById(actId)+"video.zip"
        String zipName1 = activityService.findActivityNameById(actId)+".zip";
        String zipName = new String(zipName1.getBytes("utf-8"),"ISO-8859-1");
        System.out.println("zipName="+zipName);
        String zipFilePath = Path.DOWNLOAD_ZIP_PATH+"/"+zipName;  
        System.out.println("zipFilePath = "+zipFilePath);
        //request.getServletContext().getRealPath("/");
        //System.out.println(request.getServletContext().getRealPath("/"));
        //创建需要下载的文件路径的集合 
        List<String> filePaths = new ArrayList<String>();  
        Paging<Video> pp = videoService.findVideoByActid(actId);
        
        for (Video video : pp.getData()) {
        	String ss = Path.CUT_PATH;
        	int n = video.getVideoAddress().lastIndexOf(".");
        	int m = video.getVideoAddress().indexOf(ss);
        	String addr1 =video.getVideoAddress().substring(m+11, n)+".MTS";
        	System.out.println("addr = "+addr1);
        	String addr = Path.UPLOAD_PATH+addr1; 
        	System.out.println("addr = "+addr);
        	filePaths.add(addr);
		}
        	
        //压缩文件
        ///opt/lar/files/videomanager/videoresource/tempzip
        File zip = new File(zipFilePath);  
        System.out.println("zipFilePath :"+zipFilePath);
        
        if (!zip.exists()){
        	System.out.println("start createNewFile-----------");
            zip.createNewFile();
            System.out.println("zip.exists() :"+zip.exists());
        }
        //创建zip文件输出流  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        
        this.zipFile(zipBasePath,zipName , zipFilePath,filePaths,zos);
        zos.close();
        res.setHeader("Content-disposition", "attachment;filename="+zipName);//设置下载的压缩文件名称

        //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出  
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
        byte[] buff = new byte[bis.available()];  
        bis.read(buff);
        bis.close();
        out.write(buff);//输出数据文件
        out.flush();//释放缓存
        out.close();//关闭输出流
      
        return null;
    }

    /**
     * 压缩文件
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName 临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths,ZipOutputStream zos) throws IOException {

    	
        //循环读取文件路径集合，获取每一个文件的路径  
        for(String filePath : filePaths){  
        	System.out.println("压缩文件时 每一个文件的filePath : "+filePath);
            File inputFile = new File(filePath);  //根据文件路径创建文件  
            System.out.println("判断文件是否存在"+inputFile.exists());
            if(inputFile.exists()) { //判断文件是否存在  
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹  
                	System.out.println("判断是否属于文件，还是文件夹 是文件  ");
                    //创建输入流读取文件  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

                    System.out.println("inputFile.getName() :"+inputFile.getName());
                    //将文件写入zip内，即将文件进行打包  
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));  

                    //写入文件的方法，同上                  
                    int size = 0;  
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {  
                        zos.write(buffer, 0, size);  
                    }  
                    //关闭输入输出流  
                    zos.closeEntry();  
                    bis.close(); 

                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip  
                	System.out.println("是文件夹，则使用穷举的方法获取文件，写入zip");
                    try {  
                        File[] files = inputFile.listFiles();  
                        List<String> filePathsTem = new ArrayList<String>();  
                        for (File fileTem:files) {  
                            filePathsTem.add(fileTem.toString());
                        }  
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem,zos);
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return null;
    }

@RequestMapping("/downloadVideo")
    public HttpServletResponse download(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String videoId = request.getParameter("videoId");
	String addr = videoService.findVideoAddrById(videoId);
	String s = Path.CUT_PATH;
	int n = addr.lastIndexOf(".");
	int m = addr.indexOf(s);
	String p1 =addr.substring(m+11, n)+".MTS";
	System.out.println("p1 = "+p1);
	String path1 = Path.UPLOAD_PATH+p1; 
	
        //解决乱码问题
       String path = new String(path1.getBytes("utf-8"),"utf-8");
       System.out.println("path:"+path);
        String filename = "";
        if(path != null){  //分割字符串，获取文件名称
           String file[] = path.split("/");
           filename = file[file.length-1];
        }
        //注意这里的路径必须是文件的全路径，如果是文件夹的话就会报错拒绝访问
       //path = "http://10.1.100.152:9000/resource/515演出/00312/00312.MTS";
       System.out.println(path);
        try {
           // path是指欲下载的文件的路径。
           File file = new File(path);
           // 取得文件名。
//              String filename = file.getName();
//         String filename = "周工作总结-软件部(5).xlsx";
           // 取得文件的后缀名。
           String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

           // 以流的形式下载文件。
           InputStream fis = new BufferedInputStream(new FileInputStream(file));
           byte[] buffer = new byte[fis.available()];
           fis.read(buffer);
           fis.close();
           // 清空response
           response.reset();
           // 设置response的Header
//         response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
           response.setHeader( "Content-Disposition", "attachment;filename=" + new String( filename.getBytes("GBK"), "ISO8859-1" ) );
           response.addHeader("Content-Length", "" + file.length());
           OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
           response.setContentType("application/octet-stream");
           toClient.write(buffer);
           toClient.flush();
           toClient.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return response;
     }
	public static void main(String[] args) {
		String path = "http://10.1.100.152:9000/resource/515演出/00312/00312.mp4";
		String s = Path.CUT_PATH;
		int n = path.lastIndexOf(".");
    	int m = path.indexOf(s);
    	String p1 =path.substring(m+11, n)+".MTS";
    	System.out.println("p1 = "+p1);
    	String p2 = Path.UPLOAD_PATH+p1; 
    	System.out.println("p2 = "+p2);
    	
		
	}
}
