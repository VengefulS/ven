package cn.org.cflac.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
    private String videoId;

    private String videoAddress;
    
    private String videoPicAddress;

    private Date videoUploadTime;
    
    private String videoTransform;
    
    private String invalid;
    
    private String videoMD5;
    
    private String videoName;
    
    private String videoTime;
    
    public String getDisplayVideoUploadTime(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(videoUploadTime != null){
    	return	sdf.format(videoUploadTime);
    	}
    	return null;
    }
    

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }


    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress == null ? null : videoAddress.trim();
    }

    
    
    public String getVideoPicAddress() {
		return videoPicAddress;
	}

	public void setVideoPicAddress(String videoPicAddress) {
		this.videoPicAddress = videoPicAddress;
	}


    public Date getVideoUploadTime() {
        return videoUploadTime;
    }

    public void setVideoUploadTime(Timestamp videoUploadTime) {
        this.videoUploadTime = videoUploadTime;
    }

	public String getVideoTransform() {
		return videoTransform;
	}

	public void setVideoTransform(String videoTransform) {
		this.videoTransform = videoTransform;
	}

	public String getVideoMD5() {
		return videoMD5;
	}

	public void setVideoMD5(String videoMD5) {
		this.videoMD5 = videoMD5;
	}
}