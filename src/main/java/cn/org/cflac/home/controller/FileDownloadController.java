package cn.org.cflac.home.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videod")
public class FileDownloadController {

	
	
	//普通java文件下载方法，适用于所有框架  
	@RequestMapping("/download")
    public String downloadFiles(HttpServletRequest request,HttpServletResponse res) throws IOException {
         String basePath = "http://10.1.100.152/resource/videoresource";
         
         

        //获取文件名称（包括文件格式）  
        String fileName = "video1.mp4";  

        //组合成完整的文件路径  
        String targetPath = basePath+File.separator+fileName;

        //模拟多一个文件，用于测试多文件批量下载  
        String targetPath1 = basePath+File.separator+"2.jpg";  
        //模拟文件路径下再添加个文件夹，验证穷举
        String targetPath2 = basePath+File.separator+"test";

        System.out.println("文件名："+fileName);  
        System.out.println("文件路径："+targetPath);  

       //方法1：IO流实现下载的功能  
        res.setContentType("text/html; charset=UTF-8"); //设置编码字符  
        res.setContentType("application/octet-stream"); //设置内容类型为下载类型  
        res.setHeader("Content-disposition", "attachment;filename="+fileName);//设置下载的文件名称  
        OutputStream out = res.getOutputStream();   //创建页面返回方式为输出流，会自动弹出下载框   

/*    //方法1-1：IO字节流下载，用于小文件  
        System.out.println("字节流下载");  
        InputStream is = new FileInputStream(targetPath);  //创建文件输入流  
        byte[] Buffer = new byte[2048];  //设置每次读取数据大小，即缓存大小  
        int size = 0;  //用于计算缓存数据是否已经读取完毕，如果数据已经读取完了，则会返回-1  
        while((size=is.read(Buffer)) != -1){  //循环读取数据，如果数据读取完毕则返回-1  
            out.write(Buffer, 0, size); //将每次读取到的数据写入客户端  
        }
        is.close();
        */  


/*    //方法1-2：IO字符流下载，用于大文件  
        System.out.println("字符流");  
        File file = new File(targetPath);  //创建文件  
        FileInputStream fis=new FileInputStream(file);  //创建文件字节输入流  
        BufferedInputStream bis=new BufferedInputStream(fis); //创建文件缓冲输入流  
        byte[] buffer = new byte[bis.available()];//从输入流中读取不受阻塞
        bis.read(buffer);//读取数据文件
        bis.close();
        out.write(buffer);//输出数据文件
        out.flush();//释放缓存
        out.close();//关闭输出流
*/   

    //方法1-3：将附件中多个文件进行压缩，批量打包下载文件
        //创建压缩文件需要的空的zip包  
        String zipBasePath=request.getSession().getServletContext().getRealPath("/upload/zip");  
        String zipName = "temp.zip";
        String zipFilePath = zipBasePath+File.separator+zipName;  

        //创建需要下载的文件路径的集合
        List<String> filePaths = new ArrayList<String>();  
        filePaths.add(targetPath);  
        filePaths.add(targetPath1); 
        filePaths.add(targetPath2);

        //压缩文件
        File zip = new File(zipFilePath);  
        if (!zip.exists()){     
            zip.createNewFile();     
        }
        //创建zip文件输出流  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        this.zipFile(zipBasePath,zipName, zipFilePath,filePaths,zos);
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
            File inputFile = new File(filePath);  //根据文件路径创建文件  
            if(inputFile.exists()) { //判断文件是否存在  
                if (inputFile.isFile()) {  //判断是否属于文件，还是文件夹  
                    //创建输入流读取文件  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

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

	
	
}
