package cn.org.cflac.util;

import java.io.IOException;
import java.util.List;

public class Ffmpeg {

	public static String createImg(String url){
	//视频文件   
    String videoRealPath = url;// "D://www/video1.mp4";   
    int a = videoRealPath.lastIndexOf("/");
    int b = videoRealPath.lastIndexOf(".");
    String videoPicName =  videoRealPath.substring(a+1, b);
    //截图的路径（输出路径） 
    String videoPicPath ="D://www/"+videoPicName+".jpg";
    try {   
        //调用批处理文件   
        Runtime.getRuntime().exec("cmd /c start D://ffmpeg/ffmpeg.bat " + videoRealPath + " " + videoPicPath);   
    } catch (IOException e) {   
        e.printStackTrace();   
    }
	return videoPicPath;
	}
}
