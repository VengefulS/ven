package cn.org.cflac.home.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.home.service.ActivityService;


@RestController
@RequestMapping(value="/act")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/FindAll")
	@ResponseBody
	public Paging<Activity> FindAll(Integer draw,
            @RequestParam(value = "activityName",required = false) String activityName,
            @RequestParam(value = "start",defaultValue = "0") Integer index,
            @RequestParam(value = "length",defaultValue = "10") Integer size,
            @RequestParam(value = "logdate",required = false) String logdate,
            @RequestParam(value = "starttime",required = false) String starttime,
            @RequestParam(value = "endtime",required = false) String endtime){

		/*List<Activity> list = activityService.findAll(activityId);
		Map<Integer ,Object> map = new HashMap<Integer, Object>();
		for(int i =0;i<list.size();i++){
            map.put(i, list.get(i));  
        }*/
		Paging<Activity> paging = null;
        try {
            paging = activityService.findActivityList(activityName, logdate, starttime, endtime, index, size, draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
	}
}
