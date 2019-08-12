package cn.org.cflac.home.service;

import java.util.List;
import java.util.Map;

import cn.org.cflac.entity.Paging;
import cn.org.cflac.entity.Video;

public interface VideoService {

	
	Paging<Video> findVideoByActid(String activityId);

	
	Map insertVideo(Map videoMap);
	 
	Map insertRel(Map relMap);
	Map<String,String> insertVideoTagRel(Map<String,String> relTagMap);
	
	int updateVideoTransform(Map videoMap);
	
	int deleteVideoById(Map delVideoMap);
	
	int deleteTagById(Map<String,String> delTagMap);
	 
	String isrepByAddress(String fileMd5);
	
	Paging<Video> queryAllVideo(String search,
			  Integer start,
			  Integer length,
			  Integer draw);
	
	//根据活动搜索视频
	Paging<Video> findVideoByActid2(String activityId,
			  Integer start,
			  Integer length,
			  Integer draw);


	String findVideoAddrById(String videoId);

//根据ID查找一个视频信息
	Video findvideoById(String videoId);


	void updateVideoInfo(Map<String, String> viMap);
}
