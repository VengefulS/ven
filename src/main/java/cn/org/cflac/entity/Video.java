package cn.org.cflac.entity;

import java.util.Date;

public class Video {
    private String videoId;

    private String videoName;

    private String videoAddress;

    private String videoIntroduction;

    private Date videoUploadTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress == null ? null : videoAddress.trim();
    }

    public String getVideoIntroduction() {
        return videoIntroduction;
    }

    public void setVideoIntroduction(String videoIntroduction) {
        this.videoIntroduction = videoIntroduction == null ? null : videoIntroduction.trim();
    }

    public Date getVideoUploadTime() {
        return videoUploadTime;
    }

    public void setVideoUploadTime(Date videoUploadTime) {
        this.videoUploadTime = videoUploadTime;
    }
}