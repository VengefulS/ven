package cn.org.cflac.home.service;

import java.util.List;
import java.util.Map;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.entity.Paging;



public interface ActivityService {

	List<Activity> findAll(String activityId);

	 // 返回数据
    Paging<Activity> findActivityList(String search,
    								  String activityName,
    								  String logdate,
    								  String starttime,
    								  String endtime,
    								  Integer start,
    								  Integer length,
    								  Integer draw);
    int addActivity(Activity activity);


}
