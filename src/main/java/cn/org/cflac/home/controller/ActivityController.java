package cn.org.cflac.home.controller;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;
import cn.org.cflac.home.service.ActivityService;



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
		Paging<Activity> paging = null;
        try {
            paging = activityService.findActivityList(search ,activityName, logdate, starttime, endtime, index, size, draw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paging;
	}
	
	
	
}
