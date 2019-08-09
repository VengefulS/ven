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
			return tagName + "已经存在";
		}

	}

	@RequestMapping(value = "/getAllVideoTag")
	@ResponseBody
	public List<Map<String, String>> getAllVideoTag() {
		List<Map<String, String>> list = videoTagService.findAllVideoTag();
		return list;

	}

//	@RequestMapping(value = "/deleteVideoTag")
//	@ResponseBody
//	public String deleteVideoTag(@RequestParam(value = "tagId") String tagId) {
//		videoTagService.deleteVideoTag(tagId);
//		return "删除成功";
//
//	}
	
	@RequestMapping(value = "/deleteVideoTag")
	@ResponseBody
	public String deleteVideoTag(@RequestParam(value = "tagName") String tagName) {
		String tagId = null;
		tagId = videoTagService.findTagIdByName(tagName);
		videoTagService.deleteVideoTag(tagId);
		return "删除成功";
		
	}


	@RequestMapping(value = "/updateVideoTag")
	@ResponseBody
	public String updateVideoTag(@RequestParam(value = "tagId", required = false) String tagId,
			@RequestParam(value = "tagName", required = false) String tagName) {
		List<String> list = new ArrayList<>();
		list = videoTagService.findAllVideoTagName();
		if (!list.contains(tagName)) {
			Map<String,String> videoTag = new HashMap<>();
			videoTag.put("tagId", tagId);
			videoTag.put("tagName", tagName);
			try {
				videoTagService.updateVideoTag(videoTag);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			return "修改完成";
		} else {
			return "标签已存在";
		}
	

	}

	



}
