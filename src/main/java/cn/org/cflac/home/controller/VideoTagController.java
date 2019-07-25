package cn.org.cflac.home.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.org.cflac.home.service.VideoTagService;
import cn.org.cflac.util.UUIDGenarator;
import cn.org.cflac.util.UUIDGenarator.UUID;

@RestController
@RequestMapping(value = "/videoTag")
public class VideoTagController {

	@Autowired
	private VideoTagService videoTagService;

	@RequestMapping(value = "/addVideoTag")
	@ResponseBody
	public String addVideoTag(@RequestParam(value = "tagName") String tagName) {
		List<String> list = new ArrayList<>();
		list = videoTagService.findAllVideoTagName();
		if (!list.contains(tagName)) {
			String tagId = UUIDGenarator.nextUUID();
			Map<String, String> tagMap = new HashMap<String, String>();
			tagMap.put("tagId", tagId);
			tagMap.put("tagName", tagName);
			videoTagService.insertVideoTag(tagMap);
//			JSONObject res = new JSONObject();
//			res.put(tagName, "添加成功");
			return "添加成功";
		} else {
//			JSONObject res = new JSONObject();
//			res.put(tagName, tagName+"已经存在");
			return tagName+"已经存在";
		}
		
	}
	
	@RequestMapping(value = "/getAllVideoTag")
	@ResponseBody
	public List<String> getAllVideoTag() {
		List<String> list = new ArrayList<>();
		list = videoTagService.findAllVideoTagName();
		return list;	
	}
	
	@RequestMapping(value = "/deleteVideoTag")
	@ResponseBody
	public String deleteVideoTag(@RequestParam(value = "tagName") String tagName) {
		Map<String, String> tagMap = new HashMap<String, String>();
		tagMap.put("tagName", tagName);
		videoTagService.deleteVideoTag(tagMap);
		return "删除成功";
		
	}
	
	
	@RequestMapping(value = "/matchRel")
	@ResponseBody
	public String matchRel(@RequestParam(value = "") String tagName) {
		String rid = UUIDGenarator.nextUUID();
		Map<String, String> matchMap = new HashMap<String, String>();
		matchMap.put("rid", rid);
//		tagMap.put("tagName", tagName);
//		videoTagService.insertVideoTag(tagMap);
		return null;
	}

//	@RequestMapping(value="/findActivityByTag")
//	@ResponseBody
//	public List<String> FindActivity(
//			@RequestParam(value = "videoTag",required = false) String  videoTag
//			){
//		
//		
//		List<String> list = new ArrayList<String>();
//		list = videoTagService.findAvrList(videoTag);
//		return list;
//		
//	}
//	

}
