package cn.org.cflac.home.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	 
	public FileUploadController() {
		this.log = Logger.getLogger(this.getClass());
	}
 
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file")MultipartFile files,
			HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		JSONObject json=new JSONObject();
		response.setCharacterEncoding("utf-8");
		String msg = "添加成功";
		log.info("-------------------开始调用上传文件upload接口-------------------");
		Map<String,String> map = new HashMap();
		Map<String,String> mapRel = new HashMap();
		try{
		String name = files.getOriginalFilename();
		
		String uuid = UUIDGenarator.nextUUID();
		String path = Path.UPLOAD_PATH;
		String folder = name.split("\\.")[0];
		String picPath =Path.UPLOAD_PATH+folder+".jpg";
		//"D://www"       "D://www"+folder+".jpg"
		//"http://10.1.100.152/opt/lar/files/videomanager/videoresource"
		//"http://10.1.100.152/opt/lar/files/videomanager/imgresource/"+folder+".jpg"
		path = path + "/" +name;
		//"/" + folder+ 
		System.out.println("folder:"+folder);
		System.out.println("path:"+path);
		//调用方法为上传的视频生成一个缩略图然后存到picPath中
		String videoPicPath = Ffmpeg.createImg(path);
		System.out.println("videoPicPath:"+videoPicPath);
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
	    String relActId = multiReq.getParameter("relActId");
	    System.out.println("relActId:"+relActId);
		
		
		
		map.put("uuid", uuid);
		map.put("videoAddress", path);
		map.put("videoPicAddress", videoPicPath);
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
		
		
		}catch(Exception e){
			msg="添加失败";
			e.printStackTrace();
			
		}
		log.info("-------------------结束调用上传文件upload接口-------------------");
		json.put("msg", msg);
		
		//return videoService.insertVideo(map);
		return BuildJsonOfObject.buildJsonOfJsonObject(json);
		
	}
 
	/*private byte[] inputStreamToByte(InputStream is) throws IOException {
		ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bAOutputStream.write(ch);
		}
		byte data[] = bAOutputStream.toByteArray();
		bAOutputStream.close();
		return data;
	}*/

}
