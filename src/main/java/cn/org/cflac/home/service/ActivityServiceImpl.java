package cn.org.cflac.home.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;
import cn.org.cflac.home.mapper.ActivityMapper;


@Service
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper actMapper;
	
	
	
	@Override
	public List<Activity> findAll(String activityId) {
		
		return actMapper.findAll(activityId);
	}



	@Override
	public Paging<Activity> findActivityList(String search,String activityName,String logdate, String starttime, String endtime, Integer start,Integer length, Integer draw) {
		
		// 将参数放进map中
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        List<Activity> list = null;
        map.put("search", search);
        map.put("activityName", activityName);
        map.put("logdate", logdate);
        map.put("starttime", starttime);//.replace("T", " ")
        map.put("endtime", endtime);
        map.put("start", start);
        map.put("length",length);

        map1.put("activityName", activityName);
        map1.put("logdate", logdate);
        map1.put("starttime", starttime);
        map1.put("endtime", endtime); //
        Integer count = null;
        try {
            list = actMapper.findActivityList(map);
            count = actMapper.findActivityCount(map1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paging paging = new Paging(start, length, count, draw);
        paging.setRecordsFiltered(count);
        paging.setData(list);
        return paging;
		
	}



	

}
