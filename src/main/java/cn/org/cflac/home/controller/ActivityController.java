package cn.org.cflac.home.controller;









import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.home.service.ActivityService;
import cn.org.cflac.util.UUIDGenarator;



@RestController
@RequestMapping(value="/act")
public class ActivityController {

	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="/findAll")
	@ResponseBody
	public Paging<Activity> FindAll(Integer draw,
			@RequestParam(value = "search[value]",required = false) String search,
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
		
		
		/*Enumeration<String > enums = request.getParameterNames();
		while(enums.hasMoreElements()){
			String  paramName=(String)enums.nextElement();
			String[]  values=request.getParameterValues(paramName);
			for(int  i=0;i<values.length;i++){
				System.out.println("["+i+"]   "+paramName+"  "+values[i]);
			}
		}*/

		/*System.out.println(search);
		System.out.println("start="+index);
		System.out.println("length="+size);
		*/
		
		System.out.println("search:"+search);
		Paging<Activity> paging = null;
        try {
            paging = activityService.findActivityList(search ,activityName, logdate, starttime, endtime, index, size, draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
	}
	
	@RequestMapping(value="/addActivity",method=RequestMethod.POST)
	@ResponseBody
	public String addActivity(@ModelAttribute Activity activity) {
		/*
		 Enumeration<String > enums = request.getParameterNames();
			while(enums.hasMoreElements()){
				String  paramName=(String)enums.nextElement();
				String[]  values=request.getParameterValues(paramName);
				for(int  i=0;i<values.length;i++){
					System.out.println("["+i+"]   "+paramName+"  "+values[i]);
				}
			}*/		
		activity.setActivityId(UUIDGenarator.nextUUID());
		//System.out.println(activity.getActivityId());		
		activityService.addActivity(activity);
		return "success";
		
	}
	
	@RequestMapping(value="/findOneAc",method=RequestMethod.POST)
	@ResponseBody
	public String findOneAc(@RequestParam(value="activityId") String activityId){
		
		Activity act = activityService.findActivityById(activityId);
		
		JSONObject result = new JSONObject();
		result.put("activityId", activityId);
		result.put("activityVideoGatherer", act.getActivityVideoGatherer());
		result.put("activityBeginDate",act.getDisplayActivityBeginDate());
		result.put("activitySite", act.getActivitySite());
		result.put("activityPerson", act.getActivityPerson());
		result.put("activityType", act.getActivityType());
		result.put("activityName", act.getActivityName());
		String res = result.toJSONString();
		System.out.println(res);
		return res;
		
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/updateActivity",method=RequestMethod.POST)
	@ResponseBody
	public String updateActivity(@ModelAttribute Activity activity) {
		
		activityService.updateActivity(activity);
		
		return "success";
	}
	
	@RequestMapping(value="/loginName",method=RequestMethod.POST)
	@ResponseBody
	public String loginName(HttpServletRequest req) {
		Object loginName = req.getSession().getAttribute("name");
		JSONObject result = new JSONObject();
		result.put("loginName", loginName);
		String res = result.toJSONString();
		return res;
	}
	
	
	
	
	/*@RequestMapping(value="/findActivityListByVideoTag",method=RequestMethod.POST)
	@ResponseBody
	public String findActivityListByVideoTag2(@RequestParam(value="tag") String tagName){
		
		String actId;
		actId = activityService.findActivityListByVideoTag(tagName);
		
		return null;
		
	}*/
	
	@RequestMapping(value="/findActivityListByVideoTag")
	@ResponseBody
	public Paging<Activity> findActivityListByVideoTag(Integer draw,
            @RequestParam(value="tag",required = false) String tagName,
            @RequestParam(value = "start",defaultValue = "0") Integer index,
            @RequestParam(value = "length",defaultValue = "10") Integer size,
            HttpServletRequest request
            ){
		
		/*Integer draw,*/
		/*@RequestParam(value = "search[value]",required = false) String search,*/
		/*@RequestParam(value = "start",defaultValue = "0") Integer index,
            @RequestParam(value = "length",defaultValue = "10") Integer size*/
		/*String url = request.getServletPath(); 
		System.out.println(url);*/
		System.out.println("视频的tag搜索："+tagName);
		Paging<Activity> paging = null;
        try {
            paging = activityService.findActivityList2(tagName, index, size, draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
	}
}
