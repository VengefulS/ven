package cn.org.cflac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

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
	@Rollback(false)
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

}

