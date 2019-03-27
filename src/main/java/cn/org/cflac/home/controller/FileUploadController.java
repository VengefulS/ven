package cn.org.cflac.home.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

















import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.org.cflac.home.service.ActivityService;
import cn.org.cflac.home.service.VideoService;
import cn.org.cflac.util.BuildJsonOfObject;
import cn.org.cflac.util.Ffmpeg;
import cn.org.cflac.util.Path;
import cn.org.cflac.util.UUIDGenarator;




@RestController
@RequestMapping("/videof")
public class FileUploadController {

	private Logger log;
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private ActivityService activityService;
	 
	public FileUploadController() {
		this.log = Logger.getLogger(this.getClass());
	}
 
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public JSONObject upload(@RequestParam("file")MultipartFile files,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		JSONObject json=new JSONObject();
		response.setCharacterEncoding("utf-8");
		String msg = "添加成功";
		log.info("--------开始调用上传文件upload接口-----------");
		Map<String,String> map = new HashMap();
		Map<String,String> mapRel = new HashMap();
		try{
		String name = files.getOriginalFilename();
		
		String uuid = UUIDGenarator.nextUUID();
		String path = Path.UPLOAD_PATH;
		String folder = name.split("\\.")[0];
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
	    String relActId = multiReq.getParameter("relActId");
	    System.out.println("relActId:"+relActId);
	    
	    String actName = activityService.findActivityNameById(relActId);
		System.out.println("controller中actName是："+actName);
		
		String path2 = path + "/"+actName+"/" +folder+"/"+folder+".mp4";//.mp4格式的文件地址
		path = path +"/"+actName+ "/" +folder+"/"+name;//存视频的地址 
		
		//向数据库中存的地址是在nginx中配好的
		
		
		int a = path.lastIndexOf("/");
	    int b = path.lastIndexOf(".");
	    String videoPicName =  path.substring(a+1, b);
	    
		//调用方法为上传的视频生成一个缩略图然后存到picPath中--------------

		
		
	    
	    
	    String videoPicPath1 =Path.PIC_UPLOAD_PATH+"/"+actName+"/"+folder+"/"+videoPicName+".jpg";
		
	    String videoMp4Path = Path.PIC_UPLOAD_PATH+ "/"+actName+"/" +folder+"/"+folder+".mp4";
	    
	    String videoPicPath =Path.UPLOAD_PATH+"/"+actName+"/"+folder+"/"+videoPicName+".jpg";
	    
	    
		map.put("uuid", uuid);
		map.put("videoAddress", videoMp4Path);
		map.put("videoPicAddress", videoPicPath1);
		videoService.insertVideo(map);
		/*String relActId = file.*/
		String uuidRel = UUIDGenarator.nextUUID();
		mapRel.put("uuid", uuidRel);
		mapRel.put("activityId", relActId);
		mapRel.put("videoId", uuid);
		videoService.insertRel(mapRel);
		
		File uploadFile = new File(path);
		
		if(!uploadFile.getParentFile().exists()){ //判断文件父目录是否存在
			uploadFile.getParentFile().mkdirs();
        }
		files.transferTo(uploadFile);
		System.out.println("files.transferTo() 方法调用结束---视频文件已经上传--开始转码并生成缩略图");
		String msg1 = Ffmpeg.createImg(path,folder,videoPicName,videoPicPath);
		System.out.println("videoPicPath:"+videoPicPath1+msg1);
		/*String msg2 = Ffmpeg.toMp4(path,folder,path2);
		System.out.println("Ffmpeg.toMp4 path="+path);
		System.out.println("Ffmpeg.toMp4 folder="+folder);
		System.out.println("Ffmpeg.toMp4 path2="+path2);
		System.out.println("toMp4   videoMp4Path:"+videoMp4Path+msg2);*/
		json.put("videoId", uuid);
		}catch(Exception e){
			msg="添加失败";
			e.printStackTrace();
			
		}
		log.info("--------结束调用上传文件upload接口-------");
		json.put("msg", msg);
		
		//return videoService.insertVideo(map);
		return json;
		
	}
	
	@RequestMapping(value = "/toMp4", method = RequestMethod.POST)
	public JSONObject toMp4(@RequestParam("videoName")String videoName,
			@RequestParam("actName")String actName,@RequestParam("videoId")String videoId,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String folder = videoName.split("\\.")[0];
		
		/*CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
	    String relActId = multiReq.getParameter("relActId");*/
	    
	    
		String path =Path.UPLOAD_PATH;;
		String path2 = path + "/"+actName+"/" +folder+"/"+folder+".mp4";//.mp4格式的文件地址
		path = path +"/"+actName+ "/" +folder+"/"+videoName;//存视频的地址 
		
		String msg2 = Ffmpeg.toMp4(path,folder,path2);
		System.out.println("Ffmpeg.toMp4 path="+path);
		System.out.println("Ffmpeg.toMp4 folder="+folder);
		System.out.println("Ffmpeg.toMp4 path2="+path2);
		Map videoMap = new HashMap<String, String>();
		videoMap.put("videoId", videoId);
		videoMap.put("videoTransform", "Y");
		videoService.updateVideoTransform(videoMap);
		//response.setStatus(HttpServletResponse.SC_OK);
		JSONObject json = new JSONObject();
		json.put("result",msg2);
		json.put("status","200");
		return json;
	}
}
