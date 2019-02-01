package cn.org.cflac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

import cn.org.cflac.entity.Activity;
import cn.org.cflac.home.service.ActivityService;
import cn.org.cflac.util.UUIDGenarator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoManangerApplicationTests {
	
	@Autowired
	private ActivityService activityService;
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test() throws ParseException {
		Activity acti = new Activity();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dstr="2008-04-24";
		Date date=sdf.parse(dstr);
		acti.setActivityId(UUIDGenarator.nextUUID());
		acti.setActivityName("测试");
		acti.setActivityPerson("测试");
		acti.setActivitySite("测试");
		acti.setActivityType("测试");
		acti.setActivityVideoGatherer("测试");
		acti.setActivityBeginDate(date);
		activityService.addActivity(acti);
	}
	
	@Test
	public void test1() throws JSONException {
		Activity a = activityService.findActivityById("1f46f88d186a11e9bf9814abc5682b52");
		
		System.out.println(a+"||||||||"+a.getActivityName()+"||||||"+a.getDisplayActivityBeginDate());
		Map<String, String> result = new HashMap<String, String>();
		result.put("name", a.getActivityName());
		result.put("date", a.getDisplayActivityBeginDate());
		System.out.println(result);
		System.out.println(".....................................");
		JSONObject o = new JSONObject();
		o.put("name", a.getActivityName());
		o.put("date", a.getDisplayActivityBeginDate());
		String p = o.toJSONString();
		System.out.println(p);
	}
	@Test
	//@Rollback(false)
	public void test2() {
		Activity a = activityService.findActivityById("1b3915ac688f4e8aaa64667d3523d33d");
		String activityName = "a";
		a.setActivityName(activityName);
		activityService.updateActivity(a);
		System.out.println(a.getActivityName());
		activityService.updateActivity(a);
		Activity b = activityService.findActivityById("1b3915ac688f4e8aaa64667d3523d33d");
		System.out.println(b.getActivityName());
		
	}
	
	@Test
	@Rollback(false)
	public void test3() throws Exception {
		Activity b = activityService.findActivityById("13d86f070be14feab54523e7f3e866c0");
		b.setActivityName("test");
		System.out.println(b.getActivityName());
		activityService.updateActivity(b);
		System.out.println(b.getActivityName());
	}

}

