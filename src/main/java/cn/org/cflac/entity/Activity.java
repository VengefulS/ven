package cn.org.cflac.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void setActivityBeginDate(Date activityBeginDate) {
        this.activityBeginDate = activityBeginDate;
    }

	public String getActivityVideoGatherer() {
		return activityVideoGatherer;
	}

	public void setActivityVideoGatherer(String activityVideoGatherer) {
		this.activityVideoGatherer = activityVideoGatherer;
	}

    
}