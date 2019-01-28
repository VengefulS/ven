package cn.org.cflac.entity;

import java.util.Date;

public class Video {
    private String videoId;

    private String videoAddress;
    
    private String videoPicAddress;

    private Date videoUploadTime;

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

    public void setVideoUploadTime(Date videoUploadTime) {
        this.videoUploadTime = videoUploadTime;
    }
}