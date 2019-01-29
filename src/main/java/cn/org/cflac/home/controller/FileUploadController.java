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
		String path = "D://www/";//"http://10.1.100.152/resource/videoresource"; 
		String folder = name.split("\\.")[0];
		String picPath ="http://10.1.100.152/resource/imgresource/2.jpg";
		//调用方法为上传的视频生成一个缩略图然后存到picPath中
		path = path + "/" +name;
		//"/" + folder+ 
		
		
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    MultipartHttpServletRequest multiReq = multipartResolver.resolveMultipart(request);
	    String relActId = multiReq.getParameter("relActId");
	    System.out.println(relActId);
		
		
		
		map.put("uuid", uuid);
		map.put("videoAddress", path);
		map.put("videoPicAddress", picPath);
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
 //这个方法没用到
/*	@RequestMapping(value = "/uploadservlet", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	protected String uploadServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject json=new JSONObject();
		response.setCharacterEncoding("utf-8");
		String msg = "添加成功";
		log.info("-------------------开始调用上传文件uploadservlet接口-------------------");
		try {
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
				List<MultipartFile> multipartFile = mr.getFiles("myfile");
				if (null != multipartFile && !multipartFile.isEmpty()) {
					MultipartFile file = multipartFile.get(0);
					String name = file.getOriginalFilename();
					String path = this.getClass().getClassLoader().getResource("/").getPath();
					int index = path.indexOf("Shopping");
					path = path.substring(0, index + "Shopping".length()) + "/WebContent/resources/upload/";
					path = path + File.separator + name;
					File uploadFile = new File(path);
					if(FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(uploadFile))>0)
					{
						json.put("path",path);
					}
				
				}
			}
		} catch (Exception e) {
			msg = "上传失败";
		}
		log.info("-------------------结束调用上传文件uploadservlet接口-------------------");
		json.put("msg", msg);
		return BuildJsonOfObject.buildJsonOfJsonObject(json);
	}*/
}
