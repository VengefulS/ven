package cn.org.cflac.home.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;
import cn.org.cflac.home.service.VideoService;

@RestController
@RequestMapping(value="/video")
public class VideoController {


	@Autowired
	private VideoService videoService;
	/**
	 * 查询指定活动下的所有视频
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/findVideoByActid")
	@ResponseBody
	public Paging<Video> FindVideo(
			@RequestParam(value = "activityId",required = false) String  activityId){
		
		System.out.println(activityId);
		
		Paging<Video> paging = null;
        try {
            paging = videoService.findVideoByActid(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		String fileName = "qweqw.mp4";
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName.split("\\.")[0]);
		System.out.println(uuid);
	}
	
	
/*    
    @RequestMapping("/fileUpload")
    @ResponseBody 
    public String fileUpload(@RequestParam("fileName") MultipartFile file,
    		HttpServletRequest request 
    		){


    	 Enumeration<String > enums = request.getParameterNames();
 		while(enums.hasMoreElements()){
 			String  paramName=(String)enums.nextElement();
 			String[]  values=request.getParameterValues(paramName);
 			for(int  i=0;i<values.length;i++){
 				System.out.println("["+i+"]   "+paramName+"  "+values[i]);
 			}
 		}
    	
    	
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        String folder = fileName.split("\\.")[0];
        
        String path = "F:/test/"+folder;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            
            return null;
        } catch (IllegalStateException e) {
            
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            
            e.printStackTrace();
            return "false";
        }
	
	
    }*/
}
