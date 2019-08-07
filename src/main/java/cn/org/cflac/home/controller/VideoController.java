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

import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;
import cn.org.cflac.home.service.VideoService;
import cn.org.cflac.home.service.VideoTagService;
import cn.org.cflac.util.UUIDGenarator;

@RestController
@RequestMapping(value="/video")
public class VideoController {


	@Autowired
	private VideoService videoService;
	@Autowired
	private VideoTagService videoTagService;
	/**
	 * 查询指定活动下的所有视频
	 * @param activityId
	 * @return
	 */
	@RequestMapping(value="/findVideoByActid")
	@ResponseBody
	public Paging<Video> FindVideo(
			@RequestParam(value = "activityId",required = false) String  activityId){
		
		System.out.println("当前活动ID"+activityId);
		
		Paging<Video> paging = null;
        try {
            paging = videoService.findVideoByActid(activityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
		
	}
	
	@RequestMapping(value="/deleteVideoById")
	@ResponseBody
	public String DeleteVideoById(@RequestParam(value = "videoId") String  videoId,
			@RequestParam(value = "actId") String  actId){
		
		Map<String,String> delVideoMap = new HashMap<String, String>();
		delVideoMap.put("videoId", videoId);
		delVideoMap.put("actId", actId);
		videoService.deleteVideoById(delVideoMap);
		
		
		return null;
		
	}
	
	@RequestMapping(value="/deleteMatchRelByName")
	@ResponseBody
	public String deleteMatchRelById(@RequestParam(value = "videoId",required=false) String  videoId,
			@RequestParam(value = "tagName",required=false) String  tagName){		
		String tagId = null;
		tagId = videoTagService.findTagIdByName(tagName);
		//System.out.println(tagId);
		Map<String,String> delTagMap = new HashMap<String, String>();
		delTagMap.put("videoId", videoId);
		delTagMap.put("tagId", tagId);
		//System.out.println(delTagMap.toString());
		videoService.deleteTagById(delTagMap);	
		return null;
		
	}
	
	@RequestMapping(value = "/addMatchRel")
	@ResponseBody
	public String matchRel(@RequestParam(value = "videoId",required=false) String  videoId,
			@RequestParam(value = "tagName",required=false) String  tagName) {
		String tagId = null;
		tagId = videoTagService.findTagIdByName(tagName);
		String rid = UUIDGenarator.nextUUID();
		Map<String, String> matchMap = new HashMap<String, String>();
		matchMap.put("rid", rid);
		matchMap.put("videoId", videoId);
		matchMap.put("tagId", tagId);
		videoService.insertVideoTagRel(matchMap);
		return null;
		
		
	}
	
	public static void main(String[] args) {
		String fileName = "qweqw.mp4";
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName.split("\\.")[0]);
		System.out.println(uuid);
	}
	
	
	@RequestMapping(value="/queryAllVideo")
	@ResponseBody
	public Paging<Video> QueryAllVideo(Integer draw,
			@RequestParam(value = "search[value]",required = false) String search,
            @RequestParam(value = "start",defaultValue = "0") Integer index,
            @RequestParam(value = "length",defaultValue = "10") Integer size){

		
		
		System.out.println("search:"+search);
		Paging<Video> paging = null;
        try {
            paging = videoService.queryAllVideo(search, index, size, draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return paging;
	}
	
	@RequestMapping(value="/findVideoByActid2")
	@ResponseBody
	public Paging<Video> findActivityListByVideoTag(
            @RequestParam(value="activityId",required = false) String activityId,
            @RequestParam(value = "start",defaultValue = "0") Integer index,
            @RequestParam(value = "length",defaultValue = "10") Integer size,
            Integer draw,
            HttpServletRequest request
            ){
		System.out.println("活动搜索："+activityId);
		Paging<Video> paging = null;
        try {
            paging = videoService.findVideoByActid2(activityId, index, size,draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
	}

}
