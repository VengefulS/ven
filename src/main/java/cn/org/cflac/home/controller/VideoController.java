package cn.org.cflac.home.controller;

import java.util.List;
import java.util.Map;





import net.minidev.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
