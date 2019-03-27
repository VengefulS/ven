package cn.org.cflac.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Activity {
    private String activityId;

    private String activityName;

    private String activityType;

    private String activityPerson;

    private String activitySite;
    
    private Date activityBeginDate;

    private String activityVideoGatherer;

    public String getActivityId() {
        return activityId;
    }
    
    public String getDisplayActivityBeginDate(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	if(activityBeginDate != null){
    	return	sdf.format(activityBeginDate);
    	}
    	return null;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType == null ? null : activityType.trim();
    }

    public String getActivityPerson() {
        return activityPerson;
    }

    public void setActivityPerson(String activityPerson) {
        this.activityPerson = activityPerson == null ? null : activityPerson.trim();
    }

    public String getActivitySite() {
        return activitySite;
    }

    public void setActivitySite(String activitySite) {
        this.activitySite = activitySite == null ? null : activitySite.trim();
    }

    public Date getActivityBeginDate() {
        return activityBeginDate;
    }

    //将前台日历组件传入的String类型的日期 在这里转换为Date类型
    public void setActivityBeginDate(Date activityBeginDate) {
    	/*Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(activityBeginDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		this.activityBeginDate = activityBeginDate;
    }

	public String getActivityVideoGatherer() {
		return activityVideoGatherer;
	}

	public void setActivityVideoGatherer(String activityVideoGatherer) {
		this.activityVideoGatherer = activityVideoGatherer;
	}

    
}