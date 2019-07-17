package cn.org.cflac.home.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.org.cflac.home.service.VideoTagService;

@RestController
@RequestMapping(value="/videoTag")
public class VideoTagController {

	@Autowired
	private VideoTagService videoTagService;
	
	@RequestMapping(value="/findActivityByTag")
	@ResponseBody
	public List<String> FindActivity(
			@RequestParam(value = "videoTag",required = false) String  videoTag
			){
		
		
		List<String> list = new ArrayList<String>();
		list = videoTagService.findAvrList(videoTag);
		return list;
		
	}
	
	
}
