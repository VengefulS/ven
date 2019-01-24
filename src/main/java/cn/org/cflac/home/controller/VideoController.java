package cn.org.cflac.home.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;



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
	
	@RequestMapping(value="/findVideoByActid")
	@ResponseBody
	public Paging<Video> FindVideo(
			@RequestParam(value = "activityId",required = false) String  activityId){
		
		Paging<Video> paging = null;
        try {
            paging = videoService.findVideoByActid(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
		
	}
	
    /**
     * 实现文件上传
     * */
    @RequestMapping("/fileUpload")
    @ResponseBody 
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        
        String path = "F:/test/"+fileName ;
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
	
	
    }
}
