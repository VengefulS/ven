package cn.org.cflac.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;



public class Ffmpeg {
	
	
public static void ffutil(Process process){
	try {
        //获取进程的标准输入流
        final InputStream is1 = process.getInputStream();
        //获取进城的错误流
        final InputStream is2 = process.getErrorStream();
        //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
        new Thread() {
            public void run() {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
                try {
                    String line1 = null;
                    while ((line1 = br1.readLine()) != null) {
                        if (line1 != null){}
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{
                    try {
                        is1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            public void  run() {
                BufferedReader br2 = new  BufferedReader(new  InputStreamReader(is2));
                try {
                    String line2 = null ;
                    while ((line2 = br2.readLine()) !=  null ) {
                        if (line2 != null){}
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally{
                    try {
                        is2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        Logger logger = null;
        //可能导致进程阻塞，甚至死锁
        int ret = process.waitFor();
        System.out.println("return value:"+ret);
        System.out.println(process.exitValue());
        logger.info("event:{}", "RunExeForWindows",process.exitValue());
        byte[] bytes = new byte[process.getInputStream().available()];
        process.getInputStream().read(bytes);
        System.out.println(new String(bytes));
        logger.info("event:{}", "RunExeForWindows",new String(bytes));
    }catch (Exception ex){
        ex.printStackTrace();
        try{
            process.getErrorStream().close();
            process.getInputStream().close();
            process.getOutputStream().close();
        }
        catch(Exception ee){}
    }
}

	
	
	public static String createImg(String url,String folder,String videoPicName,String videoPicPath){
	//视频文件   
    String videoRealPath = url;// "   
    //标识
    String i =null;
    
    //int a = videoRealPath.lastIndexOf("/");
    //int b = videoRealPath.lastIndexOf(".");
    //String videoPicName =  videoRealPath.substring(a+1, b);
    //截图的路径（输出路径） 
    //String videoPicPath =Path.UPLOAD_PATH+"/"+folder+"/"+videoPicName+".jpg";//"http://10.1.100.152/opt/lar/files/videomanager/imgresource/"+videoPicName+".jpg";
    //String videoPicPath1 =Path.PIC_UPLOAD_PATH+"/"+folder+"/"+videoPicName+".jpg";
    try {   
        //调用批处理文件   windows
        //Runtime.getRuntime().exec("cmd /c start"+Path.FFMPEG_PATH + videoRealPath + " " + videoPicPath);
        //调用批处理文件   linux
    	//ffmpeg -i mp4 -ss 1 -s 190*133 -f mjpeg jpg
    	System.out.println("command createImg start---------");
    	String command ="ffmpeg -i " + videoRealPath + " -ss 1 -s 190*133 -f mjpeg " + videoPicPath;
    	System.out.println("command createImg end-------");
    	Process videoProcess =
    			Runtime.getRuntime().exec(command);
    	String line;
    	BufferedReader br= new BufferedReader(new InputStreamReader(videoProcess.getErrorStream()));//,4096
    	   while ((line = br.readLine()) != null) {
    	      System.out.println(line);
    	   }
    	videoProcess.waitFor();
    	br.close();
    	i = "----createImg 方法调用结束----";
    	//videoProcess.destroy();
    	//ffutil(videoProcess);//调用方法解决死锁
    } catch (IOException e) {   
        e.printStackTrace();   
    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return i;
	}
	
	
	public static String toMp4(String url,String folder,String path2){

		String i =null;
	//String videoMp4Path = Path.PIC_UPLOAD_PATH+ "/" +folder+"/"+folder+".mp4";
	try {   
		System.out.println("command2 start---------");
		String command2 = "ffmpeg -threads 4 -i "+ url +" -c:v libx264 -strict -2 -s 1280x720 -b:v 1000k "+path2;    	
    	
    	System.out.println("command2 end-------");
    	
    	Process videoProcess2 =
    	Runtime.getRuntime().exec(command2);
    	String line;
    	BufferedReader br= new BufferedReader(new InputStreamReader(videoProcess2.getErrorStream()));//,4096
    	   while ((line = br.readLine()) != null) {
    	      System.out.println(line);
    	   }
    	videoProcess2.waitFor();
    	//videoProcess2.destroy();
    	//ffutil(videoProcess2);
    	br.close();
    	i = "----toMp4 方法调用结束----";
    } catch (IOException e) {   
        e.printStackTrace();   
    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return i;
	}
	//视频上传时调用获取视频时长
	public static String getVideoTime(String url){
		String i =null;
		String videoTime = null;
		try {
			String command2 = "ffmpeg -i "+ url;    	
	    	Process videoProcess3 = Runtime.getRuntime().exec(command2);
	    	String line;
	    	StringBuffer sb = new StringBuffer();
	    	BufferedReader br= new BufferedReader(new InputStreamReader(videoProcess3.getErrorStream()));//,4096
	    	   while ((line = br.readLine()) != null) {
	    	      System.out.println(line);
	    	      sb.append(line);
	    	   }
	    	   
	    	   br.close();
	    	   
	    	   String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
	    	   Pattern pattern = Pattern.compile(regexDuration);
	    	   Matcher m = pattern.matcher(sb.toString());
	    	   if(m.find()){
	    		   videoTime = m.group(1);
	    	   }
	    	videoProcess3.waitFor();
	    	i = "----getVideoTime 方法调用结束----";
	    } catch (IOException e) {   
	        e.printStackTrace();   
	    } catch (InterruptedException e) {
			e.printStackTrace();
		}
		return videoTime;
	}
	
	
	
}

